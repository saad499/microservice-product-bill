package com.example.billingservice.web;

import com.example.billingservice.entites.Bill;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductItemRestClient;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRepository productItemRepository;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository,
                                 CustomerRestClient customerRestClient,
                                 ProductItemRepository productItemRepository,
                                 ProductItemRestClient productItemRestClient){
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRepository = productItemRepository;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name="id") Long id){
        Bill bill = billRepository.findById(id).get();
        return bill;
    }
}
