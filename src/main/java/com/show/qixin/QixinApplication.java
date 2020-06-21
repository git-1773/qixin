package com.show.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.show.qixin.api.*.mapper")
@SpringBootApplication
public class QixinApplication {

	public static void main(String[] args) {
		SpringApplication.run(QixinApplication.class, args);
	}

}
