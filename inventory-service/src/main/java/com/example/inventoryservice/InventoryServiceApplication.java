package com.example.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(new Product(null, "Ordinateur", 700.00,50));
            productRepository.save(new Product(null, "telephone", 600.00,70));
            productRepository.save(new Product(null, "television", 1700.00,20));
            productRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });
        };
    }
}
    @Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
    class Product{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Double price;
        private int quantity;
    }

    @RepositoryRestResource
    interface ProductRepository extends JpaRepository<Product, Long>{

    }


