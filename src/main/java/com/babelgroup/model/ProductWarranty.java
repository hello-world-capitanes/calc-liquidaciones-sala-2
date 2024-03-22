package com.babelgroup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ProductWarranty extends BaseEntity {

    private boolean excluded;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private double capitalInsured;

    @ManyToOne
    private Risk cause;
    @ManyToOne
    private Warranty warranty;
    @ManyToOne
    private Product product;
}
