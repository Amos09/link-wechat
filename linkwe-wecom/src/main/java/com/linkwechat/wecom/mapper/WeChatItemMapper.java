package com.linkwechat.wecom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkwechat.wecom.domain.WeChatItem;
import com.linkwechat.wecom.domain.dto.WeChatItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天工具侧边栏
 * @author kewen
 */
public interface WeChatItemMapper extends BaseMapper<WeChatItem> {

    /**
     * 批量添加侧边栏素材
     * @param items 侧边栏素材列表
     * @return 结果
     */
    public int addItem(@Param("items") List<WeChatItem> items);

    /**
     * 删除侧边栏素材
     * @param sideId 侧边栏id
     * @return 结果
     */
    public int dropItem(@Param("sideId") Long sideId);

}
