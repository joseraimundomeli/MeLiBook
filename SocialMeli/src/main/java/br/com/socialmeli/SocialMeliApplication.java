package br.com.socialmeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMeliApplication {

	public static void main(String[] args) {
		TempTest.start();
		SpringApplication.run(SocialMeliApplication.class, args);
	}

}
