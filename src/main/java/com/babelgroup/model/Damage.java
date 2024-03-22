package com.babelgroup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Damage extends BaseEntity {

    private double newValue;
    private double initialValue;
    private int antiquity;
    private double damageCost;

    @ManyToOne
    private Warranty warranty;
    @ManyToOne
    private Sinister sinister;
}
