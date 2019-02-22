package cn.jsonXxxx.jyTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jsonXxxx
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class SpringBoot2Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}
}