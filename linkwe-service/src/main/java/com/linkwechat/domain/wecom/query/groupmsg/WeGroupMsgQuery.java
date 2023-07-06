package com.linkwechat.domain.wecom.query.groupmsg;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.linkwechat.common.enums.WeMsgTypeEnum;
import com.linkwechat.common.utils.StringUtils;
import com.linkwechat.domain.media.WeMessageTemplate;
import com.linkwechat.domain.wecom.query.WeBaseQuery;
import com.linkwechat.domain.wecom.query.WeMsgTemplateQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeGroupMsgQuery extends WeBaseQuery {


    /**
     * 欢迎语素材id
     */
    private String template_id;


    /**
     * 消息文本内容，最多4000个字节
     */
    private WeMsgTemplateQuery.Text text;


    /**
     * 图片
     */
    private WeMsgTemplateQuery.Images.Image image;


    /**
     * 链接(视频和文件都转化成h5链接)
     */
    private WeMsgTemplateQuery.Links.Link link;


    /**
     * 小程序
     */
    private WeMsgTemplateQuery.Miniprograms.Miniprogram miniprogram;


    /**
     * 授权方安装的应用agentid。仅旧的第三方多应用套件需要填此参数
     */
    private String agentid;


    /**
     * 是否通知成员将这条入群欢迎语应用到客户群中，0-不通知，1-通知， 不填则通知
     */
    private Integer notify;


    public void setAttachmentsList(String domain, List<WeMessageTemplate> messageTemplates) {
        if (CollectionUtil.isNotEmpty(messageTemplates)) {
            messageTemplates.forEach(messageTemplate -> {
                if (ObjectUtil.equal(WeMsgTypeEnum.TEXT.getMessageType(), messageTemplate.getMsgType())) {
                    this.text = new WeMsgTemplateQuery.Text();
                    text.setContent(messageTemplate.getContent());
                } else if (ObjectUtil.equal(WeMsgTypeEnum.IMAGE.getMessageType(), messageTemplate.getMsgType())) {
                    this.image= new WeMsgTemplateQuery.Images.Image(messageTemplate.getMediaId(),
                            messageTemplate.getPicUrl());
                } else if (ObjectUtil.equal(WeMsgTypeEnum.LINK.getMessageType(), messageTemplate.getMsgType())) {
                   this.link = new WeMsgTemplateQuery.Links.Link(messageTemplate.getTitle(),
                            messageTemplate.getPicUrl(), messageTemplate.getDescription(), messageTemplate.getLinkUrl());
                } else if (ObjectUtil.equal(WeMsgTypeEnum.MINIPROGRAM.getMessageType(), messageTemplate.getMsgType())) {
                    this.miniprogram= new WeMsgTemplateQuery.Miniprograms.Miniprogram(messageTemplate.getTitle(),
                            messageTemplate.getMediaId(), messageTemplate.getAppId(),
                            StringUtils.isNotEmpty(messageTemplate.getLinkUrl())
                                    ?messageTemplate.getLinkUrl(): messageTemplate.getFileUrl());
                } else if (ObjectUtil.equal(WeMsgTypeEnum.VIDEO.getMessageType(), messageTemplate.getMsgType())) { //视频

                    String materialUrl= StringUtils.isNotEmpty(messageTemplate.getLinkUrl())
                            ?messageTemplate.getLinkUrl(): messageTemplate.getFileUrl();
                    String linkUrl=domain+"/#/metrialDetail?mediaType="+WeMsgTypeEnum.VIDEO.getMessageType()+"&materialUrl="+materialUrl;

                    this.link = new WeMsgTemplateQuery.Links.Link(messageTemplate.getTitle(),
                            messageTemplate.getPicUrl(), messageTemplate.getDescription(),
                            linkUrl);



                } else if (ObjectUtil.equal(WeMsgTypeEnum.FILE.getMessageType(), messageTemplate.getMsgType())) { //文件
                    String materialUrl= StringUtils.isNotEmpty(messageTemplate.getLinkUrl())
                            ?messageTemplate.getLinkUrl(): messageTemplate.getFileUrl();
                    String linkUrl=domain+"/#/metrialDetail?mediaType="+WeMsgTypeEnum.FILE.getMessageType()+"&materialUrl="+materialUrl;

                    this.link = new WeMsgTemplateQuery.Links.Link(messageTemplate.getTitle(),
                            messageTemplate.getPicUrl(), messageTemplate.getDescription(),
                            linkUrl);


                }else if(ObjectUtil.equal(WeMsgTypeEnum.NEWS.getMessageType(), messageTemplate.getMsgType())
                        ||ObjectUtil.equal(WeMsgTypeEnum.POSTERS.getMessageType(), messageTemplate.getMsgType())){//文章或海报
                    String linkUrl=domain+ "/#/metrialDetail?materiaId=" + messageTemplate.getMaterialId();

                    this.link= new WeMsgTemplateQuery.Links.Link( messageTemplate.getTitle(),
                            messageTemplate.getPicUrl(), messageTemplate.getDescription(),
                            linkUrl);

                }
            });
        }
    }






}
