package com.alura.literatura.service;

import com.alura.literatura.main.Main;
import com.alura.literatura.repository.AuthorRepository;
import com.alura.literatura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.alura.literatura")
@EnableJpaRepositories(basePackages = "com.alura.literatura.repository")
@EntityScan(basePackages = "com.alura.literatura.model")
public class LiteraturaApplication implements CommandLineRunner {

	private final BookRepository libroRepo;
	private final AuthorRepository autorRepo;

	@Autowired
	public LiteraturaApplication(BookRepository libroRepo, AuthorRepository autorRepo) {
		this.libroRepo = libroRepo;
		this.autorRepo = autorRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Main main = new Main(libroRepo, autorRepo);
		main.mostrarMenu();
	}
}

