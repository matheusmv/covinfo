package br.edu.ifce.backend.adpters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage MESSAGE_201 = simpleMessage(201, "Resource created");
    private final ResponseMessage MESSAGE_204put = simpleMessage(204, "Updated content");
    private final ResponseMessage MESSAGE_204del = simpleMessage(204, "Deleted Content");
    private final ResponseMessage MESSAGE_403 = simpleMessage(403, "Not authorized");
    private final ResponseMessage MESSAGE_404 = simpleMessage(404, "Not found");
    private final ResponseMessage MESSAGE_422 = simpleMessage(422, "Validation error");
    private final ResponseMessage MESSAGE_500 = simpleMessage(500, "Unexpected error");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(MESSAGE_403, MESSAGE_404, MESSAGE_500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(MESSAGE_201, MESSAGE_403, MESSAGE_422, MESSAGE_500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(MESSAGE_204put, MESSAGE_403, MESSAGE_404, MESSAGE_422, MESSAGE_500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(MESSAGE_204del, MESSAGE_403, MESSAGE_404, MESSAGE_500))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.edu.ifce.backend.adpters.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "COVINFO",
                "COVINFO Project Backend",
                "Version 0.0.1",
                null,
                new Contact("Matheus Matias", "https://github.com/matheusmv", "matheus.matias.viana@hotmail.com"),
                "GNU General Public License v3.0",
                "https://github.com/matheusmv/covinfo/blob/main/LICENSE",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}
