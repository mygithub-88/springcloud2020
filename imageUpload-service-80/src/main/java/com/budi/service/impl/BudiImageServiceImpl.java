package com.budi.service.impl;

import com.budi.dao.BudiImageDao;
import com.budi.domain.BudiImage;
import com.budi.service.BudiImageService;
import com.budi.utils.uploadImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectname:cloud2020
 * @description:
 * @author:zhaoyongquan
 * @date:2020-08-12 14:37:14:37
 **/
@Service
public class BudiImageServiceImpl implements BudiImageService {
    @Resource
    private BudiImageDao budiImageDao;

    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;
    @Override
    public Map<Integer, String> uploadImage(MultipartFile[] files) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<Integer, String> map = uploadImageUtil.uploadImage(files, uploadPath, budiImageDao, request);

        return map;

        /*System.out.println("+++++++++++++++++++++"+file.getContentType());
        HashMap<Integer, String> map = new HashMap<>();
        if(file==null){
            map.put(201,"file为空");
            return map;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //当前项目请求完整url http://localhost:8090/image/upload
        String requesturl = request.getRequestURL().toString();
        System.out.println("*************************requesturl"+requesturl);
        //当前项目的请求路径 /image/upload
        String requestUri=request.getRequestURI();
        //得到去掉uri路径
        String url = requesturl.substring(0, requesturl.length() - requestUri.length() + 1);

        //存放路径
       // String path = Thread.currentThread().getContextClassLoader().getResource("static").getPath()+ "images/";
        System.out.println("-----------------------------------------------"+uploadPath);
        //String path2= ClassUtils.getDefaultClassLoader().getResource(".").getPath();
        String type = file.getContentType();//类型
        //文件名
        String filename = file.getOriginalFilename();
        //文件名加时间戳
        filename=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+filename;
        File filepath = new File(uploadPath+filename);
        //判断路径是否存在
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        try {
            file.transferTo(filepath);
            //保存到数据库的绝对路径
            String savepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+filename;
            //String savepath=url+ "images/" +filename;
            budiImageDao.uploadImage(savepath);
            map.put(200,"上传成功");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put(201,"上传失败");

        return map;*/
    }

    @Override
    public List<BudiImage> selectAll() {
        return budiImageDao.selectAll();
    }
}
