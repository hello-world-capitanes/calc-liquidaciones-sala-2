package com.babelgroup.services.liquidation;

import com.babelgroup.dtos.DtoToEntity;
import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.model.*;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidationService implements ILiquidationService {
    private final DtoToEntity dtoToEntity;

    public LiquidationService(DtoToEntity dtoToEntity) {
        this.dtoToEntity = dtoToEntity;
    }

    @Override
    public double computeSinister(SinisterDto dto){
        Sinister sinister = dtoToEntity.sinister(dto);

        double total = computeDamages(sinister);

        total = Math.min(total, sinister.getPolicy().getInsuredCapitalContent());

        if (sinister.getRealCapital() > sinister.getPolicy().getInsuredCapitalContent()){
            total *= sinister.getRealCapital() / sinister.getPolicy().getInsuredCapitalContent();
        }

        return total;
    }

    private double computeDamages(Sinister sinister){
        double total = 0;
        for(Damage damage: sinister.getDamageList()){
            total += computeDamage(damage, sinister);
        }
        return total;
    }

    private double computeDamage(Damage damage, Sinister sinister){
        ProductWarranty warranty = sinister.getPolicy().getProduct().getProductWarranties().stream().filter(w -> w.getCause().equals(sinister.getCause())).findFirst().orElseThrow();
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
        double depreciationRate = (double) 100 /years;
        double antiquity = Math.min(damage.getAntiquity(), years) - years;
        double total = damage.getInitialValue() * antiquity * depreciationRate;
        double minLiquidation = damage.getInitialValue() * 0.1;

        return Math.max(total, minLiquidation);
    }
}
