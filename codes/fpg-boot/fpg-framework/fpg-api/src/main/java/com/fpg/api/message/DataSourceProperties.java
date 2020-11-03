package com.fpg.api.message;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-3
 * @since 1.0.0
 */
@Component
@Data
public class DataSourceProperties {
    private static final String DEFAULT_MINI_IDLE = "1000";
    private static final String DEFAULT_MAX_POOL_SIZE = "50";
    private static final String DEFAULT_IDLE_TIMEOUT = "5";
    private static final String DEFAULT_CONN_TIMEOUT = "30";

    @Autowired
    private Environment environment;

    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String miniIdle;
    private String maxPoolSize;
    private String idleTimeout;
    private String connectionTimeout;

    @PostConstruct
    public void init() {
        type = environment.getProperty("type");
        driverClassName = environment.getProperty("driverClassName");
        url = environment.getProperty("url");
        username = environment.getProperty("username");
        password = environment.getProperty("password");
        miniIdle = environment.getProperty("miniIdle", DEFAULT_MINI_IDLE);
        maxPoolSize = environment.getProperty("maxPoolSize", DEFAULT_MAX_POOL_SIZE);
        idleTimeout = environment.getProperty("idleTimeout", DEFAULT_IDLE_TIMEOUT);
        connectionTimeout = environment.getProperty("connectionTimeout", DEFAULT_CONN_TIMEOUT);
    }
}
