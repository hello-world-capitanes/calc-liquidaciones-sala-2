package com.babelgroup.repositories.warranty;

import com.babelgroup.model.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWarrantyRepository extends JpaRepository<Warranty, String> {

    Optional<Warranty> findByCode(String code);
}