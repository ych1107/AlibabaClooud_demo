package com.application.controller;


import com.dftdla.result.ResponseResult;
import com.dftdlaMinio.config.MinioConfig;
import com.dftdlaMinio.util.MinioUtil;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 14501
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MinioConfig prop;

    /**
     * 查看是否存在桶
     * @param bucketName
     * @return
     */
    @GetMapping("/bucketExists")
    public ResponseResult bucketExists(@RequestParam("bucketName") String bucketName) {
        return new ResponseResult(HttpStatus.OK.value(),minioUtil.bucketExists(bucketName));
    }

    /**
     * 创建存储桶
     * @param bucketName
     * @return
     */
    @GetMapping("/makeBucket")
    public ResponseResult makeBucket(String bucketName) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), minioUtil.makeBucket(bucketName));
    }

    /**
     * 删除存储桶
     * @param bucketName
     * @return
     */
    @GetMapping("/removeBucket")
    public ResponseResult removeBucket(String bucketName) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), minioUtil.removeBucket(bucketName));
    }

    /**
     * 获取所有桶
     * @return
     */
    @GetMapping("/getAllBuckets")
    public ResponseResult getAllBuckets() {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),allBuckets);
    }

    /**
     * 文件上传返回url
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return new ResponseResult(HttpStatus.BAD_REQUEST.value(),prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName);
        }
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), null);
    }

    /**
     * 预览图片/视频
     * @param fileName
     * @return
     */
    @GetMapping("/preview")
    public ResponseResult preview(@RequestParam("fileName") String fileName) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), minioUtil.preview(fileName));
    }

    /**
     * 文件下载
     * @param fileName
     * @param res
     * @return
     */
    @GetMapping("/download")
    public ResponseResult download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName,res);
        return new ResponseResult(HttpStatus.OK.value(),null);
    }

    /**
     * 根据url删除文件
     * @param url
     * @return
     */
    @PostMapping("/delete")
    public ResponseResult remove(String url) {
        if (url == null||url.trim().length() == 0) {
            return new ResponseResult(HttpStatus.OK.value(), "url为空！");
        }
        String objName = url.substring(url.lastIndexOf(prop.getBucketName()+"/") + prop.getBucketName().length()+1);
        minioUtil.remove(objName);
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),objName);
    }

}

