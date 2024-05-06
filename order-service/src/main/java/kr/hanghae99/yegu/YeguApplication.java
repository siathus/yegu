package kr.hanghae99.yegu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class YeguApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeguApplication.class, args);
    }

}
