package com.babelgroup.repositories.risk;

import com.babelgroup.model.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRiskRepository extends JpaRepository<Risk, String> {

    Optional<Risk> findByCode(String code);
}
