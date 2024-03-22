package com.babelgroup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Policy extends BaseEntity {

    private double insuredCapitalContainer;
    private double insuredCapitalContent;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Product product;
}
