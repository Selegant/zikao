package com.annotation;

import com.config.BeetlSqlConfig;
import com.config.BeetlTemplateConfig;
import com.config.WebSimulateConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author WT
 * @CreateTime 2018/5/13
 * @Package xin.selegant.solutioncore.annotation
 **/

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Documented
@Configuration
@Import({BeetlTemplateConfig.class, BeetlSqlConfig.class, WebSimulateConfig.class})
public @interface EnableBeetlSql {
}
