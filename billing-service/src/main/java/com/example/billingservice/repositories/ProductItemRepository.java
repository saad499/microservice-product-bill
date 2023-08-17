package com.example.billingservice.repositories;

import com.example.billingservice.entites.ProdcuctItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProdcuctItem, Long> {
    public Collection<ProdcuctItem> findByBillId(@PathVariable(name = "id") Long id);
}
