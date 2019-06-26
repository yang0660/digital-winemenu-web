package com.myicellar.digitalmenu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.myicellar.digitalmenu.dao.mapper"})
public class DigitalWineMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalWineMenuApplication.class, args);
    }
}
