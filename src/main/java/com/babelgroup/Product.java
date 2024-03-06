package com.babelgroup;


import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String code;
    private String name;
    List<ProductWarranty> productWarranties;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductWarranty> getProductWarranties() {
        return productWarranties;
    }

    public void setProductWarranties(List<ProductWarranty> productWarranties) {
        this.productWarranties = productWarranties;
    }
}
