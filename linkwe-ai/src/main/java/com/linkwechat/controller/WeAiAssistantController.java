package com.linkwechat.controller;

import com.github.pagehelper.PageInfo;
import com.linkwechat.common.core.controller.BaseController;
import com.linkwechat.common.core.domain.AjaxResult;
import com.linkwechat.common.core.page.TableDataInfo;
import com.linkwechat.domain.WeAiMsg;
import com.linkwechat.domain.WeAiMsgQuery;
import com.linkwechat.domain.WeAiMsgVo;
import com.linkwechat.service.IWeAiSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping(value = "/assistant")
@Api(tags = "AI助手管理")
public class WeAiAssistantController extends BaseController {

    @Autowired
    private IWeAiSessionService iWeAiSessionService;


    @ApiOperation(value = "创建连接",httpMethod = "GET")
    @GetMapping(value = "/create/connect",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter createSseConnect(@RequestParam("sessionId") String sessionId) {
        return iWeAiSessionService.createSseConnect(sessionId);
    }

    @ApiOperation(value = "关闭连接",httpMethod = "GET")
    @GetMapping("/close/connect/{sessionId}")
    public AjaxResult closeSseConnect(@PathVariable("sessionId") String sessionId) {
        iWeAiSessionService.closeSseConnect(sessionId);
        return AjaxResult.success();
    }

    @ApiOperation(value = "发送消息",httpMethod = "POST")
    @PostMapping("/send/msg")
    public AjaxResult sendMsg(@RequestBody @Validated WeAiMsgQuery query){
        iWeAiSessionService.sendMsg(query);
        return AjaxResult.success();
    }

    @ApiOperation(value = "消息列表",httpMethod = "POST")
    @PostMapping("/msg/list")
    public TableDataInfo<List<WeAiMsgVo>> list(@RequestBody WeAiMsgQuery query){
        startPage(query.getPageIndex(),query.getPageSize());
        PageInfo<WeAiMsgVo> reslut =  iWeAiSessionService.list(query);
        return getDataTable(reslut);
    }

    @ApiOperation(value = "消息详情",httpMethod = "GET")
    @GetMapping("/msg/get/{sessionId}")
    public AjaxResult<WeAiMsgVo> getDetail(@PathVariable("sessionId") String sessionId){
        List<WeAiMsgVo> reslut =  iWeAiSessionService.getDetail(sessionId);
        return AjaxResult.success(reslut);
    }

}
