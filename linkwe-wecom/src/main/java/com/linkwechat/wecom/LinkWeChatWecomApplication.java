package com.linkwechat.wecom;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author danmo
 * @description
 * @date 2022/3/9 14:26
 **/
@MapperScan("com.linkwechat.**.mapper")
@ComponentScan("com.linkwechat.**")
@ForestScan("com.linkwechat.**")
@SpringBootApplication
public class LinkWeChatWecomApplication {
    public static void main(String[] args) {
        SpringApplication.run(LinkWeChatWecomApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  LinkWecha wecom启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}