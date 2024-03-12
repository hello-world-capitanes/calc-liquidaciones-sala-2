package com.babelgroup.repositories.productwarranty;

import com.babelgroup.model.ProductWarranty;

public interface IProductWarrantyRepository {

    ProductWarranty findWarrantyById(String id);
}
