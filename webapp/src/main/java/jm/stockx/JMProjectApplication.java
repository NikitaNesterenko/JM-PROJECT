package jm.stockx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("jm.stockx")
public class JMProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JMProjectApplication.class, args);
    }

}
