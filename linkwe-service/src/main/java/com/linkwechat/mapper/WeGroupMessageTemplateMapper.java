package com.linkwechat.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.linkwechat.domain.WeGroupMessageTemplate;

/**
 * 群发消息模板(WeGroupMessageTemplate)
 *
 * @author danmo
 * @since 2022-04-06 22:29:06
 */
@Repository()
@Mapper
public interface WeGroupMessageTemplateMapper extends BaseMapper<WeGroupMessageTemplate> {

}

