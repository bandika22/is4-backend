package hu.econsult.is4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Is4Application {

	public static void main(String[] args) {
		SpringApplication.run(Is4Application.class, args);
	}

}
