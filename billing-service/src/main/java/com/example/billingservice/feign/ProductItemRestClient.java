package com.example.billingservice.feign;

import com.example.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductItemRestClient {
    @GetMapping(path = "/products")
    //PagedModel<Product> pageProducts(@RequestParam(name="page") int page, @RequestParam(name = "size") int size);
    PagedModel<Product> pageProducts();
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable Long id);
}
