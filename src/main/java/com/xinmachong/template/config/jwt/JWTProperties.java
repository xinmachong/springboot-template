package com.xinmachong.template.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author meyer@HongYe
 * 该类存在的意义是为了:
 * 读取配置文件中 meyer.jwt.* 的各种数据类型（string，List<String>）的数据
 */
@ConfigurationProperties(prefix = "meyer.jwt")
@Component
@Getter
@Setter
public class JWTProperties {

    private int expire;
    private String signature;
    private List<String> excludePaths;
}
