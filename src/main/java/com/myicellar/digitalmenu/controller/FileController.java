package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.file.FileUploadHandle;
import com.myicellar.digitalmenu.vo.response.FileVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件Controller")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileUploadHandle fileUploadHandle;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    @AuthIgnore
    public ResultVO<FileVO> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return ResultVO.success(new FileVO(multipartFile.getOriginalFilename(), fileUploadHandle.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename())));
    }

    @ApiOperation("base64文件上传")
    @PostMapping("uploadBase64")
    @AuthIgnore
    public ResultVO<FileVO> uploadBase64(@RequestBody FileVO fileVO) throws IOException {
        if(fileVO.getOriginalFilename() == null) {
            fileVO.setOriginalFilename(String.valueOf(System.nanoTime()));
        }

        int indexOf = fileVO.getBase64Data().indexOf(";base64,");
        if(indexOf != -1) {
            indexOf += 8;
            fileVO.setBase64Data(fileVO.getBase64Data().substring(indexOf));
        }
        return ResultVO.success(new FileVO(fileVO.getOriginalFilename(), fileUploadHandle.upload(Base64.decodeBase64(fileVO.getBase64Data()), fileVO.getOriginalFilename())));
    }
}
