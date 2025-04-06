package spring.boot.core.day3.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import spring.boot.core.day3.demo.properties.CookProperties;

@SpringBootApplication
@EnableConfigurationProperties(CookProperties.class)
public class Day3Application {

	public static void main(String[] args) {
		SpringApplication.run(Day3Application.class, args);
	}

}
