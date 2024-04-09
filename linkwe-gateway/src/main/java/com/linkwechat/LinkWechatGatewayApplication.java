package com.linkwechat;


import com.linkwechat.common.config.fegin.FeginConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关启动程序
 *
 * @author leejoker
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
@EnableFeignClients(basePackages="com.linkwechat.**",defaultConfiguration = FeginConfig.class)
public class LinkWechatGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LinkWechatGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  LinkWe-gateway启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
