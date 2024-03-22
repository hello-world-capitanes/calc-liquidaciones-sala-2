package com.babelgroup.repositories.damage;

import com.babelgroup.model.Damage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDamageRepository extends JpaRepository<Damage, String> {
}
