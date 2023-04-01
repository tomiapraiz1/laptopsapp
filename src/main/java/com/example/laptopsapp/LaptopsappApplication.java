package com.example.laptopsapp;

import com.example.laptopsapp.entities.Laptop;
import com.example.laptopsapp.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopsappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LaptopsappApplication.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop l1 = new Laptop(null, "Samsung", "S10");
		Laptop l2 = new Laptop(null, "NVIDIA", "X");

		repository.save(l1);
		repository.save(l2);
		repository.save(l2);
	}

}
