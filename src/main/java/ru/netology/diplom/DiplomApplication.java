package ru.netology.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@ComponentScan( basePackages = {"ru.netology.diplom.*"} )
@EntityScan( basePackages = {"ru.netology.diplom.*"} )
@EnableJpaRepositories( basePackages = {"ru.netology.diplom.*"} )

public class DiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }
}
