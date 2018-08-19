package com.config;

import com.mapper.MapperConfig;
import com.mapper.MethodDescExt;
import com.orm.BaseDao;
import org.beetl.core.om.ObjectUtil;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.mapper.builder.MapperConfigBuilder;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(name = "beetlsql.enabled", havingValue =
        "true", matchIfMissing = true)
@Import(BeetlMapperScannerRegister.class)
public class BeetlSqlConfig {

    @Bean(name = "sqlManagerFactoryBean")
    @ConditionalOnMissingBean(SqlManagerFactoryBean.class)
    public SqlManagerFactoryBean getSqlManagerFactoryBean(BeetlSqlDataSource source, Environment env) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader==null) {
            classLoader = this.getClass().getClassLoader();
        }
        BeetlSqlProperties beetlSqlProperties = new BeetlSqlProperties(env);
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        factory.setCs(source);
        factory.setDbStyle((DBStyle) ObjectUtil.tryInstance(beetlSqlProperties.getDbStyle(source.getMasterSource())));
        factory.setInterceptors(beetlSqlProperties.dev ? new Interceptor[]{new DebugInterceptor()} : new Interceptor[0]);
        factory.setNc((NameConversion) ObjectUtil.tryInstance(beetlSqlProperties.getNameConversion()));
        ClasspathLoader loader = new ClasspathLoader(beetlSqlProperties.getSqlPath());
        //不能直接设置通过loader的autocheck
        Properties ps = new Properties();
        ps.put("PRODUCT_MODE", beetlSqlProperties.dev ? "false" : "true");
        factory.setExtProperties(ps);
        factory.setSqlLoader(loader);
        SQLManager sqlManager = factory.getObject();
        MapperConfigBuilder mcb = sqlManager.setBaseMapper(BaseDao.class).getBuilder();
        mcb.setMethodDescBuilder(MethodDescExt::new);
        //注册基础Mapper方法
        MapperConfig.INTERNAL_AMI_METHOD.forEach((key, value) -> {
            mcb.addAmi(key, value);
        });
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(BeetlSqlDataSource.class)
    public BeetlSqlDataSource beetlSqlDataSource(DataSource dataSource) {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }

    @Bean
    @ConditionalOnMissingBean(DataSourceTransactionManager.class)
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
