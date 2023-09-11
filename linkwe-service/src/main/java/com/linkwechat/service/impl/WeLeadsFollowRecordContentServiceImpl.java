package com.linkwechat.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkwechat.common.enums.leads.record.ClaimTypeEnum;
import com.linkwechat.common.enums.leads.record.FollowBackModeEnum;
import com.linkwechat.domain.leads.record.entity.WeLeadsFollowRecordContent;
import com.linkwechat.domain.leads.sea.entity.WeLeadsSea;
import com.linkwechat.mapper.WeLeadsFollowRecordContentMapper;
import com.linkwechat.mapper.WeLeadsSeaMapper;
import com.linkwechat.service.IWeLeadsFollowRecordContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 线索跟进记录内容表服务实现类
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/12 9:59
 */
@Slf4j
@Service
public class WeLeadsFollowRecordContentServiceImpl extends ServiceImpl<WeLeadsFollowRecordContentMapper, WeLeadsFollowRecordContent> implements IWeLeadsFollowRecordContentService {
    @Resource
    private WeLeadsSeaMapper weLeadsSeaMapper;

    @Override
    public void memberToReceive(Long recordId, Long seaId) {
        List<WeLeadsFollowRecordContent> list = new ArrayList<>(2);
        list.add(create(recordId, "领取方式", ClaimTypeEnum.MEMBER_TO_RECEIVE.getType(), 0));
        WeLeadsSea weLeadsSea = weLeadsSeaMapper.selectById(seaId);
        list.add(create(recordId, "所属公海", weLeadsSea.getName(), 1));
        this.saveBatch(list);
    }

    @Override
    public void autoRecovery(Long recordId) {
        List<WeLeadsFollowRecordContent> list = new ArrayList<>(2);
        list.add(create(recordId, "退回方式", "超时退回", 0));
        list.add(create(recordId, "退回原因", "超时未跟进线索", 1));
        this.saveBatch(list);
    }

    @Override
    public void adminAllocation(Long recordId, String name) {
        List<WeLeadsFollowRecordContent> list = new ArrayList<>(2);
        list.add(create(recordId, "领取方式", ClaimTypeEnum.ACTIVE_ALLOCATION.getType(), 0));
        list.add(create(recordId, "分配人", name, 1));
        this.saveBatch(list);
    }

    @Override
    public void userReturn(Long recordId, String remark, Long seaId) {
        List<WeLeadsFollowRecordContent> list = new ArrayList<>(3);
        list.add(create(recordId, "退回方式", FollowBackModeEnum.MEMBERS_RETURN.getType(), 0));
        list.add(create(recordId, "退回原因", remark, 1));
        WeLeadsSea weLeadsSea = weLeadsSeaMapper.selectById(seaId);
        list.add(create(recordId, "退回公海", weLeadsSea.getName(), 2));
        this.saveBatch(list);
    }

    @Override
    public void convertedCustomer(Long recordId, String customerName, String customerType, String avatar) {
        List<WeLeadsFollowRecordContent> list = new ArrayList<>(3);
        list.add(create(recordId, "客户名称", customerName, 0));
        list.add(create(recordId, "客户类型", customerType, 1));
        list.add(create(recordId, "客户头像", avatar, 2));
        this.saveBatch(list);
    }

    /**
     * 构建跟进记录内容
     *
     * @param recordId 跟进记录Id
     * @param key      记录项名称
     * @param value    记录项内容
     * @param rank     排序
     * @return {@link WeLeadsFollowRecordContent}
     * @author WangYX
     * @date 2023/07/12 10:27
     */
    private WeLeadsFollowRecordContent create(Long recordId, String key, String value, Integer rank) {
        return WeLeadsFollowRecordContent.builder().id(IdUtil.getSnowflake().nextId()).recordId(recordId).itemKey(key).itemValue(value).rank(rank).visible(0).attachment(0).createTime(new Date()).build();
    }

}

