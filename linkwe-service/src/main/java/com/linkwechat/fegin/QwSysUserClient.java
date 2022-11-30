package com.linkwechat.fegin;

import com.linkwechat.common.core.domain.AjaxResult;
import com.linkwechat.common.core.domain.dto.SysUserDTO;
import com.linkwechat.common.core.domain.entity.SysUser;
import com.linkwechat.fallback.QwSysUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leejoker
 * @date 2022/4/29 22:58
 */
@FeignClient(value = "${wecom.serve.linkwe-auth}", fallback = QwSysUserFallbackFactory.class, contextId = "linkwe-auth-user")
public interface QwSysUserClient {
    @GetMapping("/system/user/listAll")
    AjaxResult<List<SysUser>> listAll();

    @GetMapping("/system/user/syncUserAndDeptHandler")
    AjaxResult syncUserAndDeptHandler(@RequestParam("msg") String msg);


    @DeleteMapping("/system/user/callBackRemove/{corpId}/{userIds}")
    AjaxResult callBackRemove(@PathVariable("corpId") String corpId, @PathVariable("userIds") String[] userIds);

    @PostMapping("/system/user")
    AjaxResult add(@RequestBody SysUserDTO sysUser);

    @PutMapping("/system/user")
    AjaxResult edit(@RequestBody SysUserDTO sysUser);

    @GetMapping("/system/user/info/{id}")
    AjaxResult getUserInfoById(@PathVariable("id") Long userId);

    @PostMapping("/system/user/listByQuery")
    AjaxResult<List<SysUser>> list(@RequestBody SysUser sysUser);

    @GetMapping("/system/user/getInfo/{weUserId}")
    AjaxResult getInfo(@PathVariable("id") String weUserId);

    @GetMapping("/system/user/findAllSysUser")
    AjaxResult<List<SysUser>> findAllSysUser(@RequestParam("weUserIds") String weUserIds,
                                             @RequestParam("positions") String positions,@RequestParam("deptIds") String deptIds);

    /**
     * 根据weuserid获取员工，如果没有则从企业微信端同步
     * @param weuserId
     * @return
     */
    @GetMapping("/system/user/findOrSynchSysUser/{weuserId}")
    AjaxResult<SysUser> findOrSynchSysUser(@PathVariable("weuserId") String weuserId);



}
