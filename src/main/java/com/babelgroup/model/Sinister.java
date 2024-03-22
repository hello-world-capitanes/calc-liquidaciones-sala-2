package com.babelgroup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Sinister extends BaseEntity {

    private Date date;
    private String address;
    private double realCapital;

    @ManyToOne
    private Policy policy;
    @OneToOne
    private Risk cause;
    @OneToMany(mappedBy = "sinister")
    private List<Damage> damageList;
}
