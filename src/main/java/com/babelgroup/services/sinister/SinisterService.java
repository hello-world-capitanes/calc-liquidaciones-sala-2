package com.babelgroup.services.sinister;

import com.babelgroup.dtos.DamageDto;
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

import java.util.List;
import java.util.stream.Collectors;

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

   @Override
    public List<DamageDto> getSinisterDamages(String id) {
        Sinister sinister = sinisterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + id));
        return sinister.getDamageList().stream()
                .map(EntityToDto::damageDto)
                .collect(Collectors.toList());
    }

    @Override
    public SinisterDto addDamageToSinister(String id, DamageDto damageDto) {
        Sinister sinister = sinisterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + id));
        sinister.getDamageList().add(dtoToEntity.damage(damageDto));
        Sinister updatedSinister = sinisterRepository.save(sinister);
        return EntityToDto.sinisterDto(updatedSinister);
    }

    @Override
    public DamageDto getDamageFromSinister(String sinisterId, String damageId) {
        Sinister sinister = sinisterRepository.findById(sinisterId)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + sinisterId));
        return sinister.getDamageList().stream()
                .filter(damage -> damage.getId().equals(damageId))
                .map(EntityToDto::damageDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Damage not found with id: " + damageId));
    }

    @Override
    public DamageDto updateDamageInSinister(String sinisterId, String damageId, DamageDto updatedDamageDto) {
        Sinister sinister = sinisterRepository.findById(sinisterId)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + sinisterId));
        DamageDto damageDto = sinister.getDamageList().stream()
                .filter(damage -> damage.getId().equals(damageId))
                .map(EntityToDto::damageDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Damage not found with id: " + damageId));
        damageDto.newValue = updatedDamageDto.newValue;
        damageDto.initialValue = updatedDamageDto.initialValue;
        damageDto.antiquity = updatedDamageDto.antiquity;
        damageDto.damageCost = updatedDamageDto.damageCost;
        return damageDto;
    }

    @Override
    public void deleteDamageInSinister(String sinisterId, String damageId) {
        Sinister sinister = sinisterRepository.findById(sinisterId)
                .orElseThrow(() -> new EntityNotFoundException("Sinister not found with id: " + sinisterId));
        sinister.getDamageList().removeIf(damage -> damage.getId().equals(damageId));
        sinisterRepository.save(sinister);
    }

}
