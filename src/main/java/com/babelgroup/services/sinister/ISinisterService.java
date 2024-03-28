package com.babelgroup.services.sinister;

import com.babelgroup.dtos.DamageDto;
import com.babelgroup.dtos.SinisterDto;

import java.util.List;

public interface ISinisterService {

    SinisterDto add(SinisterDto sinister);
    SinisterDto get(String id);
    SinisterDto update(String id, SinisterDto sinister);
    List<DamageDto> getSinisterDamages(String id);
    SinisterDto addDamageToSinister(String id, DamageDto damage);
    DamageDto getDamageFromSinister(String sinisterId, String damageId);
    DamageDto updateDamageInSinister(String sinisterId, String damageId, DamageDto updatedDamage);
    void deleteDamageInSinister(String sinisterId, String damageId);


}
