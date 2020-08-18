package com.budi.dao;


import com.budi.domain.BudiImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface BudiImageDao {
    void uploadImage(String path);
    List<BudiImage> selectAll();
}
