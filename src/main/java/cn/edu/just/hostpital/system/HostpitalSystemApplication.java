package cn.edu.just.hostpital.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 扫描 Servlet 组件, 用于注册 Filter
@SpringBootApplication
public class HostpitalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostpitalSystemApplication.class, args);
    }

}
