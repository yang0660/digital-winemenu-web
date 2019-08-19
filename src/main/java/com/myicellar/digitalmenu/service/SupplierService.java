package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myicellar.digitalmenu.configuration.properties.FileUploadProperties;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.file.FileUploadHandler;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.QrcodeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
@Slf4j
public class SupplierService extends BaseService<Long, Supplier, SupplierMapperExt> {

    @Autowired
    private ImgService imgService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private FileUploadHandler fileUploadHandler;

    // 字符编码
    private static final String CHARSET = "utf-8";
    // 二维码图片格式
    private static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // 二维码url
    @Value("${supplier.indexPageUrl}")
    private String supplierIndexPageUrl;

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(SupplierReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getSupplierNameEng())) {
            throw new BizException("SupplierNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0L) {
            throw new BizException("Logo cannot be empty!");
        }
        if (reqVO.getType() == null) {
            throw new BizException("Type cannot be empty!");
        }
        if (queryBySupplierName(reqVO.getSupplierNameEng()) != null) {
            throw new BizException("Supplier already exist!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(SupplierReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("SupplierId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getSupplierNameEng())) {
            throw new BizException("SupplierNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0L) {
            throw new BizException("Logo cannot be empty!");
        }
        if (reqVO.getType() == null) {
            throw new BizException("Type cannot be empty!");
        }
        Supplier nameSupplier = queryBySupplierName(reqVO.getSupplierNameEng());
        if (nameSupplier!=null && !nameSupplier.getSupplierId().equals(reqVO.getSupplierId())) {
            throw new BizException("Supplier already exist!");
        }
    }

    /**
     * 校验删除参数
     *
     * @param reqVO
     */
    private void checkDeleteParam(SupplierDeleteReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        List<Product> products = productService.queryListBySupplierId(reqVO.getSupplierId());
        List<FoodType> foodTypes = foodTypeService.queryListBysupplierId(reqVO.getSupplierId());
        if (!products.isEmpty() || !foodTypes.isEmpty()) {
            throw new BizException("Supplier is in use, can not be deleted!");
        }
    }

    @Transactional
    public synchronized ResultVO<Integer> addNew(SupplierReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setSupplierId(snowflakeIdWorker.nextId());
        supplier.setCreatedBy(0L);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        Integer result = mapper.insertSelective(supplier);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO<Integer> update(SupplierReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setUpdatedAt(now);
        Integer result = mapper.updateByPrimaryKeySelective(supplier);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);

    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO delete(SupplierDeleteReqVO reqVO) {
        checkDeleteParam(reqVO);
        Integer result = mapper.deleteByPrimaryKey(reqVO.getSupplierId());
        if (result == 0) {
            return ResultVO.validError("delete is failed!");
        }
        return ResultVO.success("delete is success!");
    }

    /**
     * 状态切换
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO updateStatus(SupplierStatusReqVO reqVO) {
        if (reqVO.getSupplierId()==null) {
            return ResultVO.validError("SupplierId cannot be empty!");
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierId(reqVO.getSupplierId());
        supplier.setIsEnabled(reqVO.getIsEnabled());
        supplier.setUpdatedBy(1L);
        supplier.setUpdatedAt(new Date());
        Integer i = mapper.updateByPrimaryKeySelective(supplier);
        if (i == 0) {
            return ResultVO.validError("update is success!");
        }
        return ResultVO.success("update is failed!");
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<SupplierRespVO> queryPageList(SupplierPageReqVO reqVO) {
        PageResponseVO<Supplier> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        PageResponseVO<SupplierRespVO> resultPage = new PageResponseVO<SupplierRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, SupplierRespVO.class);
            List<SupplierRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (SupplierRespVO respVO : list) {
                if (respVO.getLogoImgId() != null && respVO.getLogoImgId() != 0L) {
                    imgIds.add(respVO.getLogoImgId());
                }
            }
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(info -> {
                    if (info.getLogoImgId() != null && info.getLogoImgId() != 0L) {
                        Img logoImg = imgMap.get(info.getLogoImgId());
                        if (logoImg != null) {
                            info.setLogoImgUrl(logoImg.getImgUrl());
                        }
                    }
                });
            }

            resultPage.setItems(list);
        }
        return resultPage;
    }

    /**
     * 列表查询-下拉框
     *
     * @return
     */
    public List<Supplier> queryListAll() {
        return mapper.selectListAll();
    }

    public Supplier queryBySupplierName(String supplierName) {
        return mapper.selectBySupplierName(supplierName);
    }

    /**
     * 详情查询
     *
     * @return
     */
    public SupplierRespVO queryBySupplierId(Long supplierId) {
        SupplierRespVO respVO = null;
        Supplier supplier = mapper.selectByPrimaryKey(supplierId);
        if(supplier!=null) {
            respVO = ConvertUtils.convert(supplier, SupplierRespVO.class);
            Long logoImgId = supplier.getLogoImgId();
            if (logoImgId != null && logoImgId != 0L) {
                Img img = imgService.selectByPrimaryKey(logoImgId);
                if (img!=null){
                    respVO.setLogoImgUrl(img.getImgUrl());
                }
            }
        }

        return respVO;
    }

    /**
     * 查询供应商主页二维码
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO<QrcodeRespVO> queryQrCode(SupplierIdReqVO reqVO) {
        QrcodeRespVO respVO = new QrcodeRespVO();
        String qrcodeImgUrl = "";
        Supplier supplier = mapper.selectByPrimaryKey(reqVO.getSupplierId());
        if (supplier == null) {
            return ResultVO.validError("Supplier does not exist!");
        }

        qrcodeImgUrl = supplier.getQrcodeImgUrl();
        if (StringUtils.isEmpty(qrcodeImgUrl)) {
            //生成供应商主页二维码
            FileUploadProperties.FileUploadResult qrCodeResult = generateQrCode(reqVO.getSupplierId());
            if (qrCodeResult != null && StringUtils.isNotEmpty(qrCodeResult.getImageUrl())) {
                qrcodeImgUrl = qrCodeResult.getImageUrl();
                Supplier updatesSupplier = new Supplier();
                updatesSupplier.setSupplierId(reqVO.getSupplierId());
                updatesSupplier.setQrcodeImgUrl(qrcodeImgUrl);
                //更新供应商主页二维码字段
                mapper.updateByPrimaryKeySelective(updatesSupplier);
            }
        }

        respVO.setImageUrl(qrcodeImgUrl);

        return ResultVO.success(respVO);
    }

    /**
     * 生成供应商主页二维码
     *
     * @param supplierId
     * @return
     */
    public FileUploadProperties.FileUploadResult generateQrCode(Long supplierId) {
        FileUploadProperties.FileUploadResult fileUploadResult = null;
        ByteArrayOutputStream baos = null;

        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            hints.put(EncodeHintType.MARGIN, 1);
            StringBuilder url = new StringBuilder();
            url.append(supplierIndexPageUrl).append(supplierId);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(url.toString(), BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                    hints);

            //设置二维码条纹颜色
            BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            baos = new ByteArrayOutputStream();
            ImageIO.write(image, FORMAT, baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            fileUploadResult = fileUploadHandler.upload(imageBytes, false, "jpg");
        } catch (Exception e) {
            log.error("生成供应商主页二维码失败!", e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {

                }
            }
        }

        return fileUploadResult;
    }
}