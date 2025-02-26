package io.github.gabznavas.Book.API.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // Via QUERY PARAM http://localhost:8080/api/person/v1/2?mediaType=xml
        configurer.favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true) // ignorar o parametro que vem no Header da request ex: Accept: application/json
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON) // aceita por padrão JSON
                .mediaType("json", MediaType.APPLICATION_JSON) // suporta json
                .mediaType("xml", MediaType.APPLICATION_XML); // suporta xml

    }
}
