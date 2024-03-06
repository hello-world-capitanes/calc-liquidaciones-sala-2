package com.babelgroup.model;

public class Policy {

    private Client client;
    private Product product;
    private double insuredCapital;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getInsuredCapital() {
        return insuredCapital;
    }

    public void setInsuredCapital(double insuredCapital) {
        this.insuredCapital = insuredCapital;
    }
}
