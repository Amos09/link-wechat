package com.linkwechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkwechat.domain.WeAllocateCustomer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 离职分配的客户列Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-24
 */
@Mapper
public interface WeAllocateCustomerMapper  extends BaseMapper<WeAllocateCustomer> {

    void batchAddOrUpdate(@Param("weAllocateCustomers") List<WeAllocateCustomer> weAllocateCustomer);
}