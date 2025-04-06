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


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info()
                .description("SpringDoc Simple Application Test")
                .version("1.0"));
    }

}
