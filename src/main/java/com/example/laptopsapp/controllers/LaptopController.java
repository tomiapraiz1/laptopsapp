package com.example.laptopsapp.controllers;
import com.example.laptopsapp.entities.Laptop;
import com.example.laptopsapp.repositories.LaptopRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    //listar todas las laptops
    @GetMapping("/api/laptops")
    public ArrayList<Laptop> findAll(){
        return (ArrayList<Laptop>) laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopResponseEntity = laptopRepository.findById(id);
        if (!laptopResponseEntity.isPresent()){
            log.warn("No se encontro la laptop con el id=" + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laptopResponseEntity.get());
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("Laptop a actualizar no tiene id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    //crear un objeto laptop y meterlo en la base de datos
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            log.warn("Laptop a crear no puede tener id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        Optional<Laptop> laptopResponseEntity = laptopRepository.findById(id);
        if (!laptopResponseEntity.isPresent()){
            log.warn("La laptop a borrar no existe");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.ok(laptopResponseEntity.get());
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
