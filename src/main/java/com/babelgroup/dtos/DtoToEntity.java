package com.babelgroup.dtos;

import com.babelgroup.model.Damage;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.risk.IRiskRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import org.springframework.stereotype.Service;

@Service
public class DtoToEntity {

    private final IPolicyRepository policyRepository;
    private final IWarrantyRepository warrantyRepository;
    private final IRiskRepository riskRepository;

    public DtoToEntity(IPolicyRepository policyRepository, IWarrantyRepository warrantyRepository, IRiskRepository riskRepository) {
        this.policyRepository = policyRepository;
        this.warrantyRepository = warrantyRepository;
        this.riskRepository = riskRepository;
    }

    public Sinister sinister(SinisterDto dto) {
        Sinister sinister = new Sinister();
        sinister.setPolicy(policyRepository.findById(dto.policy).orElseThrow());
        sinister.setDate(dto.date);
        sinister.setCause(riskRepository.findById(dto.cause).orElseThrow());
        sinister.setDamageList(dto.damageList.stream().map(this::damage).toList());
        sinister.setAddress(dto.address);
        sinister.setRealCapital(dto.realCapital);

        return sinister;
    }

    public Damage damage(DamageDto dto) {
        Damage damage = new Damage();
        damage.setWarranty(warrantyRepository.findById(dto.warranty).orElseThrow(() -> new RuntimeException("Warranty not found: ")));
        damage.setNewValue(dto.newValue);
        damage.setInitialValue(dto.initialValue);
        damage.setAntiquity(dto.antiquity);
        damage.setDamageCost(dto.damageCost);
        return damage;
    }

}
