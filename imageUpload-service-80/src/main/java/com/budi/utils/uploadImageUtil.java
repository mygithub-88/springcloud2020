package com.budi.utils;

import com.budi.dao.BudiImageDao;
import com.mysql.jdbc.log.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectname:cloud2020
 * @description:
 * @author:zhaoyongquan
 * @date:2020-08-14 11:28:11:28
 **/
public class uploadImageUtil {
    public static Map<Integer,String> uploadImage(MultipartFile[] files, String uploadPath, BudiImageDao b, HttpServletRequest request){
        Map map = new HashMap<>();
        System.out.println(files.length);
        if(files.length>0){
            for (int i=0;i<files.length;i++){
                MultipartFile file=files[i];
                if(file==null){
                    map.put(201,"文件为空");
                }
                //文件名
                String filename=file.getOriginalFilename();
                filename=new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+"_"+filename;
                //存放文件
                String filepath=uploadPath+filename;
                File file1 = new File(filepath);
                if(!file1.mkdirs()){
                    file1.mkdirs();
                }
                //保存文件
                try {
                    file.transferTo(file1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将文件保存到数据库
                String savePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+filename;
                try {
                    b.uploadImage(savePath);
                }catch (Exception e){
                    System.out.println("保存图片路径到数据库失败");
                }
            }
        }
        map.put(200,"文件上传成功");
        return map;
    }
}
