package com.babelgroup.model;

public class Damage {

    private ProductWarranty warranty;
    private double newValue;
    private double initialValue;
    private int antiquity;
    private double damageCost;

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

    public double getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(double damageCost) {
        this.damageCost = damageCost;
    }
}
