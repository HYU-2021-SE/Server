package com.dionysos.winecellar.domain.s3.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dionysos.winecellar.domain.s3.S3Uploader;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UploadController {
    private final S3Uploader s3Uploader;

    @PostMapping("/api/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }
}
