package com.babelgroup.model;

import lombok.Data;

@Data
public class ProductWarranty {
    private Risk risk;
    private Warranty warranty;
    private boolean excluded;
    private PaymentType paymentType;
    private double capitalInsured;

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getCapitalInsured() {
        return capitalInsured;
    }

    public void setCapitalInsured(double capitalInsured) {
        this.capitalInsured = capitalInsured;
    }
}
