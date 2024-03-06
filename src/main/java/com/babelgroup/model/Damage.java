package com.babelgroup.model;

public class Damage {

    private ProductWarranty warranty;
    private double newValue;
    private double initialValue;
    private int antiquity;

    public ProductWarranty getWarranty() {
        return warranty;
    }

    public void setWarranty(ProductWarranty warranty) {
        this.warranty = warranty;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public int getAntiquity() {
        return antiquity;
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }
}
