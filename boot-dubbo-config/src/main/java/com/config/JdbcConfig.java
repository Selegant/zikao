package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源配置文件
 * Created by leazx on 2016/12/19.
 */
@Slf4j
@Configuration()
public class JdbcConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean("dataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxActive(50);
        basicDataSource.setFilters("stat,wall");
        log.info("使用本地数据源：" + basicDataSource.getUrl());
        return basicDataSource;
    }

}
