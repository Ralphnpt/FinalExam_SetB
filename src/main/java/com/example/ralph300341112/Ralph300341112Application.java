package com.example.ralph300341112;

import com.example.ralph300341112.entities.Salesman;
import com.example.ralph300341112.respository.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SpringBootApplication
public class Ralph300341112Application {

	public static void main(String[] args) {
		SpringApplication.run(Ralph300341112Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
		return args -> {
			salesmanRepository.save(new Salesman(null, "Ralph", "washing machine", 1200.0, new Date()));
			salesmanRepository.save(new Salesman(null, "John", "refrigerator", 1000.0, new Date()));
			salesmanRepository.save(new Salesman(null, "Keith", "music system", 500.0, new Date()));
			salesmanRepository.findAll().forEach(p->{ System.out.println(p.getName());
			});
		};
	}
}