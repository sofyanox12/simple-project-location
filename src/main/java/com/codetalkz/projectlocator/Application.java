package com.codetalkz.projectlocator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codetalkz.projectlocator.api.repositories.ProjectRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final ProjectRepository projectRepository;


	public Application(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		projectRepository.findAll().forEach(System.out::println);
	}

}
