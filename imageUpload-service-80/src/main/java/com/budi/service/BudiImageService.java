package com.budi.service;

import com.budi.domain.BudiImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BudiImageService {
    Map<Integer,String> uploadImage(MultipartFile[] files);
    List<BudiImage> selectAll();
}
