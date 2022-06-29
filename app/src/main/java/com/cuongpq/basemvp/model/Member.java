package com.cuongpq.basemvp.model;

import java.io.Serializable;

public class Member implements Serializable {
    String memberAccount;
    String idAccount;
    String memberName;
    int quyen;
    float rate;

    public Member(){}

    public Member(String memberAccount, String idAccount, String memberName, int quyen, float rate) {
        this.memberAccount = memberAccount;
        this.idAccount = idAccount;
        this.memberName = memberName;
        this.quyen = quyen;
        this.rate = rate;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getQuyen() {
        return quyen;
    }

    public float getRate() {
        return rate;
    }

}
