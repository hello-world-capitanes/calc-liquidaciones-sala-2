package com.babelgroup.dtos;

import com.babelgroup.model.Damage;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.risk.IRiskRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityToDto {
    private final IPolicyRepository policyRepository;
    private final IWarrantyRepository warrantyRepository;
    private final IRiskRepository riskRepository;

        public EntityToDto(IPolicyRepository policyRepository, IWarrantyRepository warrantyRepository, IRiskRepository riskRepository) {
        this.policyRepository = policyRepository;
        this.warrantyRepository = warrantyRepository;
        this.riskRepository = riskRepository;

    }
    public static DamageDto damageDto(Damage damage) {
        DamageDto damageDto = new DamageDto();
        damageDto.id = damage.getId();
        damageDto.warranty = damage.getWarranty().getId();
        damageDto.newValue = damage.getNewValue();
        damageDto.initialValue = damage.getInitialValue();
        damageDto.antiquity = damage.getAntiquity();
        damageDto.damageCost = damage.getDamageCost();
        return damageDto;
    }

    public static SinisterDto sinisterDto(Sinister sinister) {
        SinisterDto sinisterDto = new SinisterDto();
        sinisterDto.id = sinister.getId();
        sinisterDto.policy = sinister.getPolicy().getId();
        sinisterDto.date = sinister.getDate();
        sinisterDto.cause = sinister.getCause().getId();
        sinisterDto.damageList = sinister.getDamageList().stream().map(EntityToDto::damageDto).toList();
        sinisterDto.address = sinister.getAddress();
        sinisterDto.realCapital = sinister.getRealCapital();
        return sinisterDto;
    }
}
