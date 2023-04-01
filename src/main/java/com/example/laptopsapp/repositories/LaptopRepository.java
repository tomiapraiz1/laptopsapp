package com.example.laptopsapp.repositories;
import com.example.laptopsapp.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}