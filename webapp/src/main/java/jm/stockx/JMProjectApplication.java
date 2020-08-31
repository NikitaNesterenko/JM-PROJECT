package jm.stockx;


import jm.stockx.initializer.EntityDataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan("jm.stockx")
public class JMProjectApplication extends SpringBootServletInitializer {

    @Bean(initMethod = "init")
    @PostConstruct
    public EntityDataInitializer initEntityData() {
        return new EntityDataInitializer();
    }

    public static void main(String[] args) {
        SpringApplication.run(JMProjectApplication.class, args);
    }

}
