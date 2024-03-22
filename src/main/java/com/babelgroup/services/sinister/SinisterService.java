package com.babelgroup.services.sinister;

import com.babelgroup.dtos.DtoToEntity;
import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.risk.IRiskRepository;
import com.babelgroup.repositories.sinister.ISinisterRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import org.springframework.stereotype.Service;

@Service
public class SinisterService implements ISinisterService {
    private final ISinisterRepository sinisterRepository;
    private final IPolicyRepository policyRepository;
    private final IRiskRepository riskRepository;
    private final DtoToEntity dtoToEntity;

    public SinisterService(ISinisterRepository sinisterRepository, IPolicyRepository policyRepository, IRiskRepository riskRepository, IWarrantyRepository warrantyRepository, DtoToEntity dtoToEntity) {
        this.sinisterRepository = sinisterRepository;
        this.policyRepository = policyRepository;
        this.riskRepository = riskRepository;
        this.dtoToEntity = dtoToEntity;
    }

    @Override
    public SinisterDto add(SinisterDto sinisterDto) {
        Sinister sinister = new Sinister();
        sinister.setPolicy(policyRepository.findById(sinisterDto.policy).orElseThrow());
        sinister.setDate(sinisterDto.date);
        sinister.setCause(riskRepository.findById(sinisterDto.cause).orElseThrow());
        sinister.setDamageList(sinisterDto.damageList.stream().map(dtoToEntity::damage).toList());
        sinister.setRealCapital(sinisterDto.realCapital);
        sinisterRepository.save(sinister);

        sinisterDto.id = sinister.getId();
        return sinisterDto;
    }
}
