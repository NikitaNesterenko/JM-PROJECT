package jm.stockx;


import com.vaadin.flow.spring.annotation.EnableVaadin;
import jm.stockx.initializer.EntityDataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableVaadin({"jm.stockx.controller"})
@EntityScan("jm.stockx")
public class JMProjectApplication {

    @Bean(initMethod = "init")
    @PostConstruct
    public EntityDataInitializer initEntityData() {
        return new EntityDataInitializer();
    }

    public static void main(String[] args) {
        SpringApplication.run(JMProjectApplication.class, args);
    }

}
