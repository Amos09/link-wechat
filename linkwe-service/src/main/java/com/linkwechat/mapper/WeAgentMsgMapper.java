package com.linkwechat.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkwechat.domain.agent.query.WeAgentMsgListQuery;
import com.linkwechat.domain.agent.vo.WeAgentMsgListVo;
import com.linkwechat.domain.agent.vo.WeAgentMsgVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.linkwechat.domain.WeAgentMsg;

/**
 * 应用消息表(WeAgentMsg)
 *
 * @author danmo
 * @since 2022-11-04 17:08:08
 */
@Mapper
public interface WeAgentMsgMapper extends BaseMapper<WeAgentMsg> {


    WeAgentMsgVo getMsgInfo(@Param("id") Long id);

    List<WeAgentMsgListVo> getMsgList(WeAgentMsgListQuery query);
}

