package com.babelgroup.repositories;

import com.babelgroup.model.*;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.product.IProductRepository;
import com.babelgroup.repositories.productwarranty.IProductWarrantyRepository;
import com.babelgroup.repositories.risk.IRiskRepository;
import com.babelgroup.repositories.sinister.ISinisterRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BaseData {
    private final IRiskRepository riskRepository;
    private final IWarrantyRepository warrantyRepository;
    private final IProductRepository productRepository;
    private final IProductWarrantyRepository productWarrantyRepository;


    public BaseData(IRiskRepository riskRepository, IWarrantyRepository warrantyRepository, IProductRepository productRepository, IProductWarrantyRepository productWarrantyRepository) {
        this.riskRepository = riskRepository;
        this.warrantyRepository = warrantyRepository;
        this.productRepository = productRepository;
        this.productWarrantyRepository = productWarrantyRepository;
    }

    public void init() {
        createRisks();
        createWarranties();
        createProducts();
    }

    private void createRisks() {
        Risk riskRoberyOutside = new Risk();
        riskRoberyOutside.setCode("RO");
        riskRoberyOutside.setName("Robery outside");
        Risk riskWind = new Risk();
        riskWind.setCode("WI");
        riskWind.setName("Wind Over 50Kmh");
        Risk riskMoisture = new Risk();
        riskMoisture.setCode("MS");
        riskMoisture.setName("Moisture");

        riskRepository.save(riskRoberyOutside);
        riskRepository.save(riskMoisture);
        riskRepository.save(riskWind);
    }

    private void createWarranties() {
        Warranty warrantyRoberyOutside = new Warranty();
        warrantyRoberyOutside.setCode("RO");
        warrantyRoberyOutside.setName("Robery outside");
        warrantyRoberyOutside.setWarrantyType(WarrantyType.CONTENT);
        Warranty warrantyRoof = new Warranty();
        warrantyRoof.setCode("RF");
        warrantyRoof.setName("Roof");
        warrantyRoof.setWarrantyType(WarrantyType.BUILDING);
        Warranty warrantyGeneralBuilding = new Warranty();
        warrantyGeneralBuilding.setCode("GB");
        warrantyGeneralBuilding.setName("General Building");
        warrantyGeneralBuilding.setWarrantyType(WarrantyType.BUILDING);
        Warranty warrantyHomeAppliances = new Warranty();
        warrantyHomeAppliances.setCode("HA");
        warrantyHomeAppliances.setName("Home Appliances");
        warrantyHomeAppliances.setWarrantyType(WarrantyType.CONTENT);

        warrantyRepository.save(warrantyRoberyOutside);
        warrantyRepository.save(warrantyRoof);
        warrantyRepository.save(warrantyGeneralBuilding);
        warrantyRepository.save(warrantyHomeAppliances);
    }

    private void createProducts() {
        Product product = new Product();
        product.setCode("HOGAR15");
        product.setName("AXA Hogar 15");

        product.setProductWarranties(this.createProductWarranties());
        productRepository.save(product);
    }

    private List<ProductWarranty> createProductWarranties() {
        List<ProductWarranty> warranties = new ArrayList<ProductWarranty>();

        ProductWarranty warrantyRoberyOutside = new ProductWarranty();
        warrantyRoberyOutside.setExcluded(false);
        warrantyRoberyOutside.setCapitalInsured(300.0);
        warrantyRoberyOutside.setCause(riskRepository.findByCode("RO").orElseThrow());
        warrantyRoberyOutside.setPaymentType(PaymentType.PRIMER_RIESGO);
        warrantyRoberyOutside.setWarranty(warrantyRepository.findByCode("RO").orElseThrow());
        ProductWarranty warrantyRoberyOutsideRoof = new ProductWarranty();
        warrantyRoberyOutsideRoof.setExcluded(true);
        warrantyRoberyOutsideRoof.setCause(riskRepository.findByCode("RO").orElseThrow());
        warrantyRoberyOutsideRoof.setWarranty(warrantyRepository.findByCode("RF").orElseThrow());
        ProductWarranty warrantyRoberyGeneralBuilding = new ProductWarranty();
        warrantyRoberyGeneralBuilding.setExcluded(true);
        warrantyRoberyGeneralBuilding.setCause(riskRepository.findByCode("RO").orElseThrow());
        warrantyRoberyGeneralBuilding.setWarranty(warrantyRepository.findByCode("GB").orElseThrow());
        ProductWarranty warrantyRoberyOutsideHomeAppliance = new ProductWarranty();
        warrantyRoberyOutsideHomeAppliance.setExcluded(true);
        warrantyRoberyOutsideHomeAppliance.setCause(riskRepository.findByCode("RO").orElseThrow());
        warrantyRoberyOutsideHomeAppliance.setWarranty(warrantyRepository.findByCode("HA").orElseThrow());

        warranties.add(warrantyRoberyOutside);
        productWarrantyRepository.save(warrantyRoberyOutside);
        warranties.add(warrantyRoberyOutsideRoof);
        productWarrantyRepository.save(warrantyRoberyOutsideRoof);
        warranties.add(warrantyRoberyGeneralBuilding);
        productWarrantyRepository.save(warrantyRoberyGeneralBuilding);
        warranties.add(warrantyRoberyOutsideHomeAppliance);
        productWarrantyRepository.save(warrantyRoberyOutsideHomeAppliance);

        ProductWarranty warrantyWindRoof = new ProductWarranty();
        warrantyWindRoof.setExcluded(false);
        warrantyWindRoof.setCapitalInsured(1300);
        warrantyWindRoof.setCause(riskRepository.findByCode("WI").orElseThrow());
        warrantyWindRoof.setPaymentType(PaymentType.REPOSICION_NUEVO);
        warrantyWindRoof.setWarranty(warrantyRepository.findByCode("RF").orElseThrow());
        ProductWarranty warrantyWindRoberyOutside = new ProductWarranty();
        warrantyWindRoberyOutside.setExcluded(true);
        warrantyWindRoberyOutside.setCause(riskRepository.findByCode("WI").orElseThrow());
        warrantyWindRoberyOutside.setWarranty(warrantyRepository.findByCode("RO").orElseThrow());
        ProductWarranty warrantyWindGeneralBuilding = new ProductWarranty();
        warrantyWindGeneralBuilding.setExcluded(true);
        warrantyWindGeneralBuilding.setCause(riskRepository.findByCode("WI").orElseThrow());
        warrantyWindGeneralBuilding.setWarranty(warrantyRepository.findByCode("GB").orElseThrow());
        ProductWarranty warrantyWindHomeAppliance = new ProductWarranty();
        warrantyWindHomeAppliance.setExcluded(true);
        warrantyWindHomeAppliance.setCause(riskRepository.findByCode("WI").orElseThrow());
        warrantyWindHomeAppliance.setWarranty(warrantyRepository.findByCode("HA").orElseThrow());

        warranties.add(warrantyWindRoof);
        productWarrantyRepository.save(warrantyWindRoof);
        warranties.add(warrantyWindRoberyOutside);
        productWarrantyRepository.save(warrantyWindRoberyOutside);
        warranties.add(warrantyWindGeneralBuilding);
        productWarrantyRepository.save(warrantyWindGeneralBuilding);
        warranties.add(warrantyWindHomeAppliance);
        productWarrantyRepository.save(warrantyWindHomeAppliance);

        ProductWarranty warrantyMoistureRoof = new ProductWarranty();
        warrantyMoistureRoof.setExcluded(false);
        warrantyMoistureRoof.setCause(riskRepository.findByCode("MS").orElseThrow());
        warrantyMoistureRoof.setWarranty(warrantyRepository.findByCode("RF").orElseThrow());
        ProductWarranty warrantyMoistureRoberyOutside = new ProductWarranty();
        warrantyMoistureRoberyOutside.setExcluded(true);
        warrantyMoistureRoberyOutside.setCause(riskRepository.findByCode("MS").orElseThrow());
        warrantyMoistureRoberyOutside.setWarranty(warrantyRepository.findByCode("RO").orElseThrow());
        ProductWarranty warrantyMoistureGeneralBuilding = new ProductWarranty();
        warrantyRoberyOutsideRoof.setExcluded(false);
        warrantyRoberyOutsideRoof.setCapitalInsured(20000);
        warrantyRoberyOutsideRoof.setCause(riskRepository.findByCode("MS").orElseThrow());
        warrantyRoberyOutsideRoof.setWarranty(warrantyRepository.findByCode("GB").orElseThrow());
        ProductWarranty warrantyMoistureHomeAppliance = new ProductWarranty();
        warrantyMoistureHomeAppliance.setExcluded(false);
        warrantyMoistureHomeAppliance.setCapitalInsured(3000);
        warrantyMoistureHomeAppliance.setCause(riskRepository.findByCode("MS").orElseThrow());
        warrantyMoistureHomeAppliance.setWarranty(warrantyRepository.findByCode("HA").orElseThrow());

        warranties.add(warrantyMoistureRoof);
        productWarrantyRepository.save(warrantyMoistureRoof);
        warranties.add(warrantyMoistureRoberyOutside);
        productWarrantyRepository.save(warrantyMoistureRoberyOutside);
        warranties.add(warrantyMoistureGeneralBuilding);
        productWarrantyRepository.save(warrantyMoistureGeneralBuilding);
        warranties.add(warrantyMoistureHomeAppliance);
        productWarrantyRepository.save(warrantyMoistureHomeAppliance);

        return warranties;

    }




}
