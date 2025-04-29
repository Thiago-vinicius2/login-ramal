package com.desafio.estagio.LoginRamal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5500")  // URL do Live Server
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // MÉTODOS PERMITIDOS
                .allowedHeaders("*");  // PERMITE TODOS OS CABEÇALHOS
    }
}

