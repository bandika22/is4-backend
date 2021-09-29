package hu.econsult.is4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("hu.econsult.is4"))
                .paths(regex("/rest.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("E-Consult 2000 Kft. - IS4");
        apiInfoBuilder.description("It is far more than a single documentation...");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Total nice License");
        apiInfoBuilder.licenseUrl("http://li.cense");
        apiInfoBuilder.termsOfServiceUrl("terms.of.service.url");
        return apiInfoBuilder.build();
    }
}