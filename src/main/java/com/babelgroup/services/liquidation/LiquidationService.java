package com.babelgroup.services.liquidation;

import com.babelgroup.dtos.DtoToEntity;
import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.model.Damage;
import com.babelgroup.model.ProductWarranty;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.productwarranty.IProductWarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidationService implements ILiquidationService {

    private final IPolicyRepository policyRepository;
    private final IProductWarrantyRepository productWarrantyRepository;

    public LiquidationService(IPolicyRepository policyRepository, IProductWarrantyRepository productWarrantyRepository) {
        this.policyRepository = policyRepository;
        this.productWarrantyRepository = productWarrantyRepository;
    }

    public double computeSinister(SinisterDto dto){
        DtoToEntity converter = new DtoToEntity(policyRepository, productWarrantyRepository);

        Sinister sinister = converter.sinister(dto);

        double total = computeDamages(sinister.getDamageList());

        total = Math.min(total, sinister.getPolicy().getInsuredCapitalContent());

        if (sinister.getRealCapital() > sinister.getPolicy().getInsuredCapitalContent()){
            total *= sinister.getRealCapital() / sinister.getPolicy().getInsuredCapitalContent();
        }

        return total;
    }

    private double computeDamages(List<Damage> damageList){
        double total = 0;
        for(Damage damage: damageList){
            total += computeDamage(damage);
        }
        return total;
    }

    private double computeDamage(Damage damage) {
        ProductWarranty warranty = damage.getWarranty();
        if (warranty.isExcluded()){
            return 0;
        }

        double total = 0;

        switch (warranty.getPaymentType()){
            case PRIMER_RIESGO -> total += damage.getDamageCost();
            case REPOSICION_NUEVO -> total += damage.getNewValue();
            case VALOR_REAL -> total += computeRealValue(damage);
        }

        return Math.max(total, warranty.getCapitalInsured());
    }

    private double computeRealValue(Damage damage) {
        int years = 7;
        double depreciationRate = 100/7;
        double antiquity = Math.min(damage.getAntiquity(), years) - years;
        double total = damage.getInitialValue() * antiquity * depreciationRate;
        double minLiquidation = damage.getInitialValue() * 0.1;

        return Math.max(total, minLiquidation);
    }
}
