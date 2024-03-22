package com.babelgroup.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends BaseEntity {

    @Column(unique = true)
    private String code;
    private String name;

    @OneToMany(mappedBy = "product")
    private List<ProductWarranty> productWarranties;
    @OneToMany(mappedBy = "product")
    private List<Policy> policyList;
}
