package com.show.qixin.api.common.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        parameters.add(parameterBuilder.build());
//        ModelCache.getInstance().setParamClass(GlobalString.class);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.show.qixin"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }
    public ApiInfo apiInfo(){
        Contact contact=new Contact("ypj","http://www.ypj.com","1773050899@qq.com");
        return new ApiInfoBuilder()
                .title("企信企业信息管理系统 API文档")
                .description("talk is cheap , show me the code~")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
