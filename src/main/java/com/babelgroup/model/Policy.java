package com.babelgroup.model;

public class Policy {

    private Client client;
    private Product product;
    private double insuredCapitalContainer;
    private double insuredCapitalContent;

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

    public double getInsuredCapitalContainer() {
        return insuredCapitalContainer;
    }

    public void setInsuredCapitalContainer(double insuredCapitalContainer) {
        this.insuredCapitalContainer = insuredCapitalContainer;
    }

    public double getInsuredCapitalContent() {
        return insuredCapitalContent;
    }

    public void setInsuredCapitalContent(double insuredCapitalContent) {
        this.insuredCapitalContent = insuredCapitalContent;
    }
}
