package com.babelgroup.dtos;

import com.babelgroup.model.Damage;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.productwarranty.IProductWarrantyRepository;
import com.babelgroup.repositories.policy.IPolicyRepository;

public class DtoToEntity {

    private final IPolicyRepository policyRepository;
    private final IProductWarrantyRepository productWarrantyRepository;

    public DtoToEntity(IPolicyRepository policyRepository, IProductWarrantyRepository productWarrantyRepository) {
        this.policyRepository = policyRepository;
        this.productWarrantyRepository = productWarrantyRepository;
    }

    public Sinister sinister(SinisterDto dto){
        Sinister sinister = new Sinister();
        sinister.setPolicy(policyRepository.findPolicyById(dto.policy));
        sinister.setDate(dto.date);
        sinister.setCause(dto.cause);
        sinister.setDamageList(dto.damageList.stream().map(this::damage).toList());
        sinister.setAddress(dto.address);
        sinister.setRealCapital(dto.realCapital);

        return sinister;
    }

    public Damage damage(DamageDto dto){
        Damage damage = new Damage();
        damage.setWarranty(productWarrantyRepository.findWarrantyById(dto.warranty));
        damage.setNewValue(dto.newValue);
        damage.setInitialValue(dto.initialValue);
        damage.setAntiquity(dto.antiquity);
        damage.setDamageCost(dto.damageCost);
        return damage;
    }
}
