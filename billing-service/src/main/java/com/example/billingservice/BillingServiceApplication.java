package com.example.billingservice;

import com.example.billingservice.entites.Bill;
import com.example.billingservice.entites.ProdcuctItem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductItemRestClient;
import com.example.billingservice.models.Customer;
import com.example.billingservice.models.Product;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRestClient productItemRestClient,
                            CustomerRestClient customerRestClient,
                            ProductItemRepository productItemRepository){
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            System.out.println(customer.getName());
            System.out.println(customer.getEmail());

            Bill bill = billRepository.save(new Bill(null, new Date(),null,customer.getId(),null));
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProdcuctItem prodcuctItem = new ProdcuctItem();
                prodcuctItem.setPrice(p.getPrice());
                prodcuctItem.setQuantity(1+new Random().nextInt(100));
                prodcuctItem.setBill(bill);
                productItemRepository.save(prodcuctItem);
            });
        };
    }
}
