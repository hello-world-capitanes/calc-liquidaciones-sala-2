package com.babelgroup.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warranty extends BaseEntity {

    @Column(unique = true)
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private WarrantyType warrantyType;

    @OneToMany(mappedBy = "warranty")
    private List<Damage> damageList;
}
