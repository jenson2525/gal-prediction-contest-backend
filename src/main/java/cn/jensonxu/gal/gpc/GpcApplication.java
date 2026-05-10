package cn.jensonxu.gal.gpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jensonxu.gal.gpc.repository.mapper")
public class GpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpcApplication.class, args);
    }

}
