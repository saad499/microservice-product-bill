package com.example.billingservice.entites;

import com.example.billingservice.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @OneToMany(mappedBy="bill")
    private Collection<ProdcuctItem> prodcuctItems;
    private Long CustomerID;
    @Transient
    public Customer customer;
}
