package com.babelgroup.services.sinister;

import com.babelgroup.dtos.SinisterDto;

public interface ISinisterService {

    SinisterDto add(SinisterDto sinister);
    SinisterDto get(String id);

}
