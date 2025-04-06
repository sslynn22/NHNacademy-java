package spring.boot.core.day2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import spring.boot.core.day2.demo.properties.CookProperties;

@SpringBootApplication
@EnableConfigurationProperties(CookProperties.class)
public class Day2Application {
	public static void main(String[] args) {
		SpringApplication.run(Day2Application.class, args);
	}

}
