package org.reportes;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.util.List;
import org.springframework.context.annotation.Bean;


import org.thymeleaf.dialect.springdata.SpringDataDialect;

/**
 * Created by tiansha on 2015/11/3.
 */
@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "guru.springframework")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, 25));
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }    

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}
