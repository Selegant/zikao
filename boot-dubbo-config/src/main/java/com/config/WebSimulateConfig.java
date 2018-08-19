package com.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.simulate.WebSimulate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(WebSimulate.class)
@ConditionalOnBean(GroupTemplate.class)
public class WebSimulateConfig {

	@Bean
	@ConditionalOnBean(ObjectMapper.class)
	public WebSimulate getWebSimulate(GroupTemplate gt, ObjectMapper objectMapper){
		return new WebSimulate(gt,new ObjectMapperJsonUtil(objectMapper) );
	}
}
