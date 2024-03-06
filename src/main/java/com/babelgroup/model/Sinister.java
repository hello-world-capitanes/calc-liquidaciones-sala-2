package com.babelgroup.model;

import java.util.Date;
import java.util.List;

public class Sinister {

    private Policy policy;
    private Date date;
    private String cause;
    private List<Damage> damageList;
    private String address;
    private double realCapital;

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public List<Damage> getDamageList() {
        return damageList;
    }

    public void setDamageList(List<Damage> damageList) {
        this.damageList = damageList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(double realCapital) {
        this.realCapital = realCapital;
    }
}
