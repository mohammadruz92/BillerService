package com.pst.efi.biller.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class SubPmtsEntity {

    @Column(name = "amount")
    private String amount;

    @Column(name = "set_bnk_code")
    private int setBnkCode;

    @Column(name = "acct_no")
    private String acctNo;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getSetBnkCode() {
        return setBnkCode;
    }

    public void setSetBnkCode(int setBnkCode) {
        this.setBnkCode = setBnkCode;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
}
