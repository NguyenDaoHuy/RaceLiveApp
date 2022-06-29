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

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberAccount='" + memberAccount + '\'' +
                ", idAccount='" + idAccount + '\'' +
                ", memberName='" + memberName + '\'' +
                ", quyen=" + quyen +
                '}';
    }
}
