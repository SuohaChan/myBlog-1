package com.tree.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.tree.domain.ResponseResult;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.exception.SystemException;
import com.tree.service.UploadService;
import com.tree.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssUploadServiceImpl implements UploadService {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //如果判断通过上传文件到OSS
        try {
            String url = aliOSSUtils.upload(img);
            return ResponseResult.okResult(url);
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
