package com.yoxwei.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    private static final String separator = ";";

    /**
     * 声明基础包
     * @param basePackage 基础包路径
     * @return
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    /**
     * 校验基础包
     * @param basePackage 基础包路径
     * @return
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(separator)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    @SuppressWarnings("deprecation")
    private static Optional<? extends Class<?>> declaringClass(RequestHandler requestHandler) {
        return Optional.fromNullable(requestHandler.declaringClass());
    }

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实力，用来控制哪些接口暴露给Swagger来展现
     * @return
     */
    @Bean
    public Docket createRestApi() {
        String basePackages = "com.yoxwei";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(basePackage(basePackages))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("This API Document is based on RESTful Style, The description is detail and auto-generation, It's very friendly for developers.")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

}
