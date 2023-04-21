package com.application.controller;


import com.dftdla.result.ResponseResult;
import com.dftdlaMinio.config.MinioConfig;
import com.dftdlaMinio.util.MinioUtil;
import io.minio.messages.Bucket;
import io.swagger.annotations.*;
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
@Api(tags = "文件操作接口")
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
    @ApiOperation(value = "查看是否存在桶",notes = "查看是否存在桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "bucketName",value = "桶名",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "判断成功！")
    })
    @GetMapping("/bucketExists")
    public ResponseResult bucketExists(@RequestParam("bucketName") String bucketName) {
        return new ResponseResult(HttpStatus.OK.value(),minioUtil.bucketExists(bucketName));
    }

    /**
     * 创建存储桶
     * @param bucketName
     * @return
     */
    @ApiOperation(value = "创建存储桶",notes = "创建存储桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "bucketName",value = "桶名",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "创建成功！")
    })
    @GetMapping("/makeBucket")
    public ResponseResult makeBucket(String bucketName) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), minioUtil.makeBucket(bucketName));
    }

    /**
     * 删除存储桶
     * @param bucketName
     * @return
     */
    @ApiOperation(value = "删除存储桶",notes = "删除存储桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "bucketName",value = "桶名",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功！")
    })
    @GetMapping("/removeBucket")
    public ResponseResult removeBucket(String bucketName) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), minioUtil.removeBucket(bucketName));
    }

    /**
     * 获取所有桶
     * @return
     */
    @ApiOperation(value = "获取所有桶",notes = "获取所有桶")
    @ApiImplicitParam(paramType = "header", name = "token", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取成功！")
    })
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
    @ApiOperation(value = "文件上传返回url",notes = "文件上传返回url")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "file",value = "上传的文件",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 400, message = "上传失败！")
    })
    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return new ResponseResult(HttpStatus.OK.value(),prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName);
        }
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), null);
    }

    /**
     * 预览图片/视频
     * @param fileName
     * @return
     */
    @ApiOperation(value = "预览图片/视频",notes = "返回预览图片/视频 url")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "fileName",value = "‘/’+文件目录+‘/’+文件名",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回成功！")
    })
    @GetMapping("/preview")
    public ResponseResult preview(@RequestParam("fileName") String fileName) {
        return new ResponseResult(HttpStatus.OK.value(), minioUtil.preview(fileName));
    }

    /**
     * 文件下载
     * @param fileName
     * @param res
     * @return
     */
    @ApiOperation(value = "文件下载",notes = "文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "fileName",value = "‘/’+文件目录+‘/’+文件名",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "文件正在下载中---")
    })
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
    @ApiOperation(value = "根据url删除文件",notes = "根据url删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "url",value = "上传文件时返回的url",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功！"),
            @ApiResponse(code = 400, message = "url为空！")
    })
    @PostMapping("/delete")
    public ResponseResult remove(String url) {
        if (url == null||url.trim().length() == 0) {
            return new ResponseResult(HttpStatus.BAD_REQUEST.value(), "url为空！");
        }
        String objName = url.substring(url.lastIndexOf(prop.getBucketName()+"/") + prop.getBucketName().length()+1);
        minioUtil.remove(objName);
        return new ResponseResult(HttpStatus.OK.value(),objName);
    }

}

