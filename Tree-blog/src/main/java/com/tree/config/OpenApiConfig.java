//package com.tree.config;
//
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI springOpenAPI() {
//        return new OpenAPI().info(new Info()
//                .description("SpringDoc Simple Application Test")
//                .version("1.0"));
//    }
//
//}
// blog-module/src/main/java/com/tree/blog/config/OpenApiConfig.java
package com.tree.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "博客模块接口文档",
                version = "1.0",
                description = "仅包含博客相关接口"
        )
)


public class OpenApiConfig {
    /**
     * 仅扫描博客模块的Controller
     */
    @Bean
    public GroupedOpenApi blogApi() {
        return GroupedOpenApi.builder()
                .group("blog")  // 自定义分组名称
                .packagesToScan("com.tree.controller.controller") // 指定博客模块的Controller包
                .build();
    }
}