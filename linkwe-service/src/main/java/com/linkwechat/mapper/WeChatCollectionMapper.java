package com.linkwechat.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkwechat.domain.side.vo.WeChatSideVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.linkwechat.domain.WeChatCollection;

/**
 * 素材收藏表(WeChatCollection)
 *
 * @author danmo
 * @since 2022-05-25 17:56:59
 */
@Mapper
public interface WeChatCollectionMapper extends BaseMapper<WeChatCollection> {


    List<WeChatSideVo> findCollections(@Param("userId") Long userId, @Param("keyword") String keyword);
}

