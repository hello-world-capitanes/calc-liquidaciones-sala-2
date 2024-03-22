package com.babelgroup.dtos;

import java.util.Date;
import java.util.List;

public class SinisterDto {
    public String id;
    public String policy;
    public Date date;
    public String cause;
    public List<DamageDto> damageList;
    public String address;
    public double realCapital;
}
