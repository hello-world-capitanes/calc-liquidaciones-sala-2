package com.babelgroup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Client extends BaseEntity {

    private String name;
    private String address;
    private String nif;

    @OneToMany(mappedBy = "client")
    private List<Policy> policyList;
}
