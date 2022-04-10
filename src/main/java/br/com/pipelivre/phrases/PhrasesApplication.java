package br.com.pipelivre.phrases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class PhrasesApplication {
	public static Integer DEFAULT_PAGE_SIZE = 20;

	public static void main(String[] args) {
		SpringApplication.run(PhrasesApplication.class, args);
	}

}
