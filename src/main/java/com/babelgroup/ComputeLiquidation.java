package com.babelgroup;

import com.babelgroup.model.Damage;
import com.babelgroup.model.ProductWarranty;
import com.babelgroup.model.Sinister;

import java.util.List;

public class ComputeLiquidation {

    public static double compute(Sinister sinister){

        double total = computeDamages(sinister.getDamageList());

        total = Math.min(total, sinister.getPolicy().getInsuredCapitalContent());

        if (sinister.getRealCapital() > sinister.getPolicy().getInsuredCapitalContent()){
            total *= sinister.getRealCapital() / sinister.getPolicy().getInsuredCapitalContent();
        }

        return total;
    }

    private static double computeDamages(List<Damage> damageList){
        double total = 0;
        for(Damage damage: damageList){
            total += computeDamage(damage);
        }
        return total;
    }

    private static double computeDamage(Damage damage) {
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

    private static double computeRealValue(Damage damage) {
        int years = 7;
        double depreciationRate = 100/7;
        double antiquity = Math.min(damage.getAntiquity(), years) - years;
        double total = damage.getInitialValue() * antiquity * depreciationRate;
        double minLiquidation = damage.getInitialValue() * 0.1;

        return Math.max(total, minLiquidation);
    }
}
