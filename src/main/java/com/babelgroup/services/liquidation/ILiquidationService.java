package com.babelgroup.services.liquidation;

import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.model.Sinister;

public interface ILiquidationService {

    double computeSinister(SinisterDto sinister);
}
