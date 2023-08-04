package com.linkwechat.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.linkwechat.common.constant.Constants;
import com.linkwechat.common.core.controller.BaseController;
import com.linkwechat.common.core.domain.AjaxResult;
import com.linkwechat.common.core.page.TableDataInfo;
import com.linkwechat.common.enums.substitute.customer.order.SubstituteCustomerOrderCataloguePropertyTypeEnum;
import com.linkwechat.domain.substitute.customer.order.entity.WeSubstituteCustomerOrderCatalogueProperty;
import com.linkwechat.domain.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyAddRequest;
import com.linkwechat.domain.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyMoveRequest;
import com.linkwechat.domain.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyRequest;
import com.linkwechat.domain.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyUpdateRequest;
import com.linkwechat.domain.substitute.customer.order.vo.WeSubstituteCustomerOrderCataloguePropertyVO;
import com.linkwechat.service.IWeSubstituteCustomerOrderCataloguePropertyService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 代客下单分类字段 前端控制器
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@RestController
@RequestMapping("/substitute/customer/order/property")
public class WeSubstituteCustomerOrderCataloguePropertyController extends BaseController {

    @Resource
    private IWeSubstituteCustomerOrderCataloguePropertyService weSubstituteCustomerOrderCataloguePropertyService;

    /**
     * 列表
     *
     * @param
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/08/03 9:58
     */
    @ApiOperation("列表")
    @GetMapping("/page")
    public TableDataInfo list(@Validated WeSubstituteCustomerOrderCataloguePropertyRequest request) {
        startPage();
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogueProperty> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogueProperty.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getCatalogueId, request.getCatalogueId());
        List<WeSubstituteCustomerOrderCatalogueProperty> list = weSubstituteCustomerOrderCataloguePropertyService.list(queryWrapper);
        TableDataInfo dataTable = getDataTable(list);
        List<WeSubstituteCustomerOrderCataloguePropertyVO> vos = BeanUtil.copyToList(list, WeSubstituteCustomerOrderCataloguePropertyVO.class);
        vos.stream().forEach(i -> i.setTypeStr(SubstituteCustomerOrderCataloguePropertyTypeEnum.byCode(i.getType())));
        dataTable.setRows(vos);
        return dataTable;
    }

    /**
     * 新增
     *
     * @param request 新增请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 10:50
     */
    @ApiOperation("新增")
    @PostMapping("")
    public AjaxResult add(@Validated @RequestBody WeSubstituteCustomerOrderCataloguePropertyAddRequest request) {
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogueProperty> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogueProperty.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.COMMON_STATE);
        int count = weSubstituteCustomerOrderCataloguePropertyService.count(queryWrapper);

        WeSubstituteCustomerOrderCatalogueProperty property = BeanUtil.copyProperties(request, WeSubstituteCustomerOrderCatalogueProperty.class);
        property.setId(IdUtil.getSnowflakeNextId());
        property.setSort(count);
        property.setFixed(0);
        property.setDelFlag(Constants.COMMON_STATE);
        weSubstituteCustomerOrderCataloguePropertyService.save(property);
        return AjaxResult.success(property.getId());
    }


    /**
     * 修改
     *
     * @param request 修改请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 10:50
     */
    @ApiOperation("修改")
    @PutMapping("")
    public AjaxResult update(@Validated @RequestBody WeSubstituteCustomerOrderCataloguePropertyUpdateRequest request) {
        WeSubstituteCustomerOrderCatalogueProperty property = weSubstituteCustomerOrderCataloguePropertyService.getById(request.getId());
        if (BeanUtil.isEmpty(property)) {
            return AjaxResult.success();
        }
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogueProperty> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogueProperty.class);
        updateWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getId, request.getId());
        updateWrapper.set(request.getCatalogueId() != null, WeSubstituteCustomerOrderCatalogueProperty::getCatalogueId, request.getCatalogueId());
        if (property.getFixed().equals(0)) {
            updateWrapper.set(StrUtil.isNotBlank(request.getName()), WeSubstituteCustomerOrderCatalogueProperty::getName, request.getName());
        }
        updateWrapper.set(request.getRequired() != null, WeSubstituteCustomerOrderCatalogueProperty::getRequired, request.getRequired());
        updateWrapper.set(StrUtil.isNotBlank(request.getExpound()), WeSubstituteCustomerOrderCatalogueProperty::getExpound, request.getExpound());
        updateWrapper.set(StrUtil.isNotBlank(request.getValue()), WeSubstituteCustomerOrderCatalogueProperty::getValue, request.getValue());
        updateWrapper.set(request.getMoney() != null, WeSubstituteCustomerOrderCatalogueProperty::getMoney, request.getMoney());
        updateWrapper.set(request.getToTime() != null, WeSubstituteCustomerOrderCatalogueProperty::getToTime, request.getToTime());
        updateWrapper.set(request.getMultipleChoice() != null, WeSubstituteCustomerOrderCatalogueProperty::getMultipleChoice, request.getMultipleChoice());
        updateWrapper.set(request.getMore() != null, WeSubstituteCustomerOrderCatalogueProperty::getMore, request.getMore());
        weSubstituteCustomerOrderCataloguePropertyService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 删除
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 11:14
     */
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogueProperty> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogueProperty.class);
        updateWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getId, id);
        updateWrapper.set(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.DELETE_STATE);
        weSubstituteCustomerOrderCataloguePropertyService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 移动
     *
     * @param
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 11:17
     */
    @ApiOperation("移动")
    @PutMapping("/move")
    public AjaxResult move(@RequestBody WeSubstituteCustomerOrderCataloguePropertyMoveRequest request) {
        weSubstituteCustomerOrderCataloguePropertyService.move(request);
        return AjaxResult.success();
    }

    /**
     * 字段类型列表
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 15:35
     */
    @ApiOperation("字段类型列表")
    @GetMapping("/type")
    public AjaxResult typeList() {
        SubstituteCustomerOrderCataloguePropertyTypeEnum[] values = SubstituteCustomerOrderCataloguePropertyTypeEnum.values();
        Map map = new HashMap(values.length);
        Arrays.stream(values).forEach(i -> map.put(i.getCode(), i.getType()));
        return AjaxResult.success(map);
    }

    /**
     * 详情
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 16:36
     */
    @ApiOperation("详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        WeSubstituteCustomerOrderCatalogueProperty one = weSubstituteCustomerOrderCataloguePropertyService.getById(id);
        WeSubstituteCustomerOrderCataloguePropertyVO vo = BeanUtil.copyProperties(one, WeSubstituteCustomerOrderCataloguePropertyVO.class);
        vo.setTypeStr(SubstituteCustomerOrderCataloguePropertyTypeEnum.byCode(vo.getType()));
        return AjaxResult.success(vo);
    }

    /**
     * 属性
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 16:59
     */
    @ApiOperation("属性")
    @GetMapping("/properties")
    public AjaxResult properties() {
        return AjaxResult.success( weSubstituteCustomerOrderCataloguePropertyService.properties());
    }

}
