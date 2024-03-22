package com.babelgroup.repositories.product;

import com.babelgroup.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByCode(String code);
}
