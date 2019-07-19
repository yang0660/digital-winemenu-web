package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myicellar.digitalmenu.configuration.properties.FileUploadProperties;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.service.SupplierService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.utils.file.FileUploadHandler;
import com.myicellar.digitalmenu.vo.request.SupplierDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierReqVO;
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
    private SnowflakeIdWorker snowflakeIdWorker;

    @Value("${supplier.indexPageUrl}")
    private String supplierIndexPageUrl;

    /**
     * 供应商下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("供应商下拉列表")
    public ResultVO<List<SupplierRespVO>> queryListPage() {
        List<Supplier> list = supplierService.queryListAll();

        List<SupplierRespVO> resultList = new ArrayList<SupplierRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,SupplierRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<SupplierRespVO>> queryListPage(@RequestBody SupplierPageReqVO reqVO) {
        PageResponseVO<Supplier> page = supplierService.queryPageList(reqVO);

        PageResponseVO<SupplierRespVO> resultPage = new PageResponseVO<SupplierRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,SupplierRespVO.class);
        }

        return ResultVO.success(resultPage);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<SupplierRespVO> add(@RequestBody SupplierReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO,Supplier.class);
        supplier.setSupplierId(snowflakeIdWorker.nextId());
        supplier.setCreatedBy(0L);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        int i = supplierService.insertSelective(supplier);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        SupplierRespVO respVO = ConvertUtils.convert(supplier,SupplierRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody SupplierReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO,Supplier.class);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setUpdatedAt(now);
        int i = supplierService.updateByPrimaryKeySelective(supplier);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        SupplierRespVO respVO = ConvertUtils.convert(supplier,SupplierRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody SupplierDeleteReqVO reqVO) {
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = supplierService.deleteByPrimaryKey(reqVO.getSupplierId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(SupplierReqVO reqVO){
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getSupplierNameEng())){
            throw new BizException("SupplierNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(SupplierReqVO reqVO){
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("SupplierId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getSupplierNameEng())){
            throw new BizException("SupplierNameEng cannot be empty!");
        }
    }

    /**
     * 查询供应商主页二维码
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryQrCode")
    @AuthIgnore
    @ApiOperation("查询供应商主页二维码")
    private ResultVO<QrcodeRespVO> queryQrCode(@RequestBody SupplierIdReqVO reqVO) {

        QrcodeRespVO respVO =new QrcodeRespVO();
        String qrcodeImgUrl = "";
        Supplier supplier = supplierService.selectByPrimaryKey(reqVO.getSupplierId());
        if(supplier==null){
            return ResultVO.validError("Supplier does not exist!");
        }

        qrcodeImgUrl = supplier.getQrcodeImgUrl();
        if(StringUtils.isEmpty(qrcodeImgUrl)) {
            //生成供应商主页二维码
            FileUploadProperties.FileUploadResult qrCodeResult = generateQrCode(reqVO.getSupplierId());
            if(qrCodeResult!=null && StringUtils.isNotEmpty(qrCodeResult.getImageUrl())){
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
     * @param supplierId
     * @return
     */
    public FileUploadProperties.FileUploadResult generateQrCode(Long supplierId){
        FileUploadProperties.FileUploadResult fileUploadResult= null;
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
        }catch (Exception e){
            log.error("生成供应商主页二维码失败!",e);
        }finally {
            if(baos!=null){
                try {
                    baos.close();
                }catch (Exception e){

                }
            }
        }

        return fileUploadResult;
    }


}
