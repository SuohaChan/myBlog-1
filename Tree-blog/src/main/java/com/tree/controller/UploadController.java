package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.ResponseResult;
import com.tree.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @mySystemLog(xxbusinessName = "上传头像")
    public ResponseResult upload(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
