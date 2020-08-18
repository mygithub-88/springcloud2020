package com.budi.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.druid.support.json.JSONUtils;
import com.budi.domain.BudiImage;
import com.budi.service.BudiImageService;
import com.budi.utils.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectname:cloud2020
 * @description:
 * @author:zhaoyongquan
 * @date:2020-08-12 13:22:13:22
 **/
@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageUploadController {
    @Resource
    private BudiImageService budiImageService;

    /**
     * 上传图片
     * @param files
     * @return
     */
    @RequestMapping("upload")
    public Map<Integer,String> uploadImage(@RequestParam("org_files")MultipartFile[] files){
        //Map result=new HashMap<Integer,String>();
        //result.put(200,"上传成功");

        return budiImageService.uploadImage(files);
    }
    /**
     * 查询所有图片
     */
    @RequestMapping("selectAll")
    public ResultMessage selectAll(){
        List<BudiImage> images = budiImageService.selectAll();
        return  new ResultMessage(200,"查询成功",images);
    }
}
