package jm.config;


import jm.config.initializer.EntityDataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan("jm")
@ComponentScan("jm")
public class JMProjectApplication {

    @Bean(initMethod = "init")
    @PostConstruct
    public EntityDataInitializer initEntityData() {
        return new EntityDataInitializer();
    }

    public static void main(String[] args) {
        SpringApplication.run(JMProjectApplication.class, args);

        new EntityDataInitializer();
    }

}
