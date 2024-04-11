package com.linkwechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkwechat.domain.material.entity.WeCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leejoker
 * @date 2022/4/1 21:13
 */
@Mapper
public interface WeCategoryMapper extends BaseMapper<WeCategory> {

    List<WeCategory> categoryList(@Param("mediaType") String mediaType);
}
