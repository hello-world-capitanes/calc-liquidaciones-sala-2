package com.babelgroup.services.sinister;

import com.babelgroup.dtos.DtoToEntity;
import com.babelgroup.dtos.EntityToDto;
import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.model.Sinister;
import com.babelgroup.repositories.policy.IPolicyRepository;
import com.babelgroup.repositories.risk.IRiskRepository;
import com.babelgroup.repositories.sinister.ISinisterRepository;
import com.babelgroup.repositories.warranty.IWarrantyRepository;
import jakarta.persistence.EntityNotFoundException;
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
        sinister.setPolicy(policyRepository.findById(sinisterDto.policy)
                .orElseThrow(() -> new EntityNotFoundException("Policy not found")));
        sinister.setDate(sinisterDto.date);
        sinister.setCause(riskRepository.findById(sinisterDto.cause)
                .orElseThrow(() -> new EntityNotFoundException("Cause not found")));
        sinister.setDamageList(sinisterDto.damageList.stream().map(dtoToEntity::damage).toList());
        sinister.setRealCapital(sinisterDto.realCapital);
        sinisterRepository.save(sinister);

        sinisterDto.id = sinister.getId();
        return sinisterDto;
    }

    @Override
    public SinisterDto get(String id){
        Sinister sinister = sinisterRepository.findById(id).orElseThrow();
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

    @Override
    public SinisterDto update(String id, SinisterDto sinisterDto){
        Sinister sinister = sinisterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + id));

        sinister.setPolicy(policyRepository.findById(sinisterDto.policy)
                .orElseThrow(() -> new EntityNotFoundException("Policy not found")));

        sinister.setDate(sinisterDto.date);

        sinister.setCause(riskRepository.findById(sinisterDto.cause)
                .orElseThrow(() -> new EntityNotFoundException("Cause not found")));

        sinister.setDamageList(sinisterDto.damageList.stream().map(dtoToEntity::damage).toList());
        sinister.setRealCapital(sinisterDto.realCapital);
        Sinister updatedSinister = sinisterRepository.save(sinister);
        return EntityToDto.sinisterDto(updatedSinister);
    }

}
