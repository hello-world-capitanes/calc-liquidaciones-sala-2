package com.babelgroup.repositories.sinister;

import com.babelgroup.model.Sinister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISinisterRepository extends JpaRepository<Sinister, String> {
}