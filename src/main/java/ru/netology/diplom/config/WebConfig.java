
package ru.netology.diplom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc

class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // разрешить запросы от всех доменов
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // разрешенные методы
                .allowedHeaders("*") // разрешить все заголовки
                .allowCredentials(true) // разрешить использование куки
                .maxAge(3600); // время жизни предварительных запросов в секундах
    }
}

