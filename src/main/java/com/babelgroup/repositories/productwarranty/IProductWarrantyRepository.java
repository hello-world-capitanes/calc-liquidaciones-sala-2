package com.babelgroup.repositories.productwarranty;

import com.babelgroup.model.ProductWarranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductWarrantyRepository extends JpaRepository<ProductWarranty, String> {
}
