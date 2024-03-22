package com.babelgroup.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    private String id = UUID.randomUUID().toString();
}
