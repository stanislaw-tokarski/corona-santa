package com.github.stanislawtokarski.corona.santa;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.github.stanislawtokarski.corona.santa")
@EnableVaadin(value = "com.github.stanislawtokarski.corona.santa.ui")
public class CoronaSantaRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CoronaSantaRunner.class, args);
    }
}
