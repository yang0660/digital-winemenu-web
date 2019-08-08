package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myicellar.digitalmenu.configuration.properties.FileUploadProperties;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.service.SupplierService;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.utils.file.FileUploadHandler;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.QrcodeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/manage/supplier")
@Api(tags = "供应商管理", description = "/manage/supplier")
public class SupplierManageController {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;

    @Autowired
    FileUploadHandler fileUploadHandler;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FoodTypeService foodTypeService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Value("${supplier.indexPageUrl}")
    private String supplierIndexPageUrl;

    /**
     * 供应商下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryList")
    @ApiOperation("供应商下拉列表")
    public ResultVO<List<SupplierRespVO>> queryListPage() {
        List<Supplier> list = supplierService.queryListAll();

        List<SupplierRespVO> resultList = new ArrayList<SupplierRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, SupplierRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 列表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询-分页")
    public ResultVO<PageResponseVO<SupplierRespVO>> queryListPage(@RequestBody SupplierPageReqVO reqVO) {
        PageResponseVO<SupplierRespVO> page = supplierService.queryPageList(reqVO);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            return ResultVO.success(page);
        }
        return ResultVO.success(new PageResponseVO<SupplierRespVO>());
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryBySupplierId")
    @ApiOperation("详情查询")
    public ResultVO<SupplierRespVO> queryByWineryId(@RequestBody SupplierIdReqVO reqVO) {

        SupplierRespVO respVO = supplierService.queryBySupplierId(reqVO.getSupplierId());
        if (respVO == null) {
            respVO = new SupplierRespVO();
        }
        return ResultVO.success(respVO);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody SupplierReqVO reqVO) {

        //参数校验
        checkNewParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setSupplierId(snowflakeIdWorker.nextId());
        supplier.setCreatedBy(0L);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        return ResultVO.success(supplierService.insertSelective(supplier));
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO<Integer> update(@RequestBody SupplierReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setUpdatedAt(now);
        return ResultVO.success(supplierService.updateByPrimaryKeySelective(supplier));
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO<Integer> delete(@RequestBody SupplierDeleteReqVO reqVO) {
        checkDeleteParam(reqVO);
        return ResultVO.success(supplierService.deleteByPrimaryKey(reqVO.getSupplierId()));

    }

    /**
     * 状态切换
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/updateStatus")
    @ApiOperation("状态切换")
    public ResultVO<Integer> updateStatus(@RequestBody SupplierStatusReqVO reqVO) {
        if (reqVO.getSupplierId()==null) {
            return ResultVO.validError("SupplierId cannot be empty!");
        }
        return ResultVO.success(supplierService.updateStatus(reqVO));
    }

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
        if (supplierService.queryBySupplierName(reqVO.getSupplierNameEng()) != null) {
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
        Supplier supplier = supplierService.selectByPrimaryKey(reqVO.getSupplierId());
        if (!reqVO.getSupplierNameEng().equals(supplier.getSupplierNameEng())) {
            Supplier nameSupplier = supplierService.queryBySupplierName(reqVO.getSupplierNameEng());
            if (nameSupplier!=null && !nameSupplier.getSupplierId().equals(reqVO.getSupplierId())) {
                throw new BizException("Supplier already exist!");
            }
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


    /**
     * 查询供应商主页二维码
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryQrCode")
    @ApiOperation("查询供应商主页二维码")
    private ResultVO<QrcodeRespVO> queryQrCode(@RequestBody SupplierIdReqVO reqVO) {

        QrcodeRespVO respVO = new QrcodeRespVO();
        String qrcodeImgUrl = "";
        Supplier supplier = supplierService.selectByPrimaryKey(reqVO.getSupplierId());
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
                supplierService.updateByPrimaryKeySelective(updatesSupplier);
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
