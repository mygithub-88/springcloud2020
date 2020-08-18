package com.budi.domain;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectname:cloud2020
 * @description:
 * @author:zhaoyongquan
 * @date:2020-08-12 14:16:14:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudiImage {
    Integer id;
    String imagePath;
}
