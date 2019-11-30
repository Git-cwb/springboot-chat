package cn.com.zxh.stringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.com.zxh")
public class StringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StringbootApplication.class, args);
    }

}
