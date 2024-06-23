package com.pst.efi.biller.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "payment_notification")
public class PaymentNotificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "timestamp")
  private String timestamp;

  @Column(name = "guid")
  private String guid;

  @Column(name = "sdr_code")
  private int sdrCode;

  @Column(name = "rcv_code")
  private int rcvCode;

  @Column(name = "req_type")
  private String reqType;

  @Column(name = "billing_no")
  private String billingNo;

  @Column(name = "bill_no")
  private String billNo;

  @Column(name = "joe_bpps_trx")
  private String joeBppsTrx;

  @Column(name = "bank_trx_id")
  private String bankTrxId;

  @Column(name = "bank_code")
  private String bankCode;

  @Column(name = "pmt_status")
  private String pmtStatus;

  @Column(name = "due_amt")
  private String dueAmt;

  @Column(name = "paid_amt")
  private String paidAmt;

  @Column(name = "fees_amt")
  private String feesAmt;

  @Column(name = "fees_on_biller")
  private boolean feesOnBiller;

  @Column(name = "process_date")
  private String processDate;

  @Column(name = "stmt_date")
  private String stmtDate;

  @Column(name = "access_channel")
  private String accessChannel;

  @Column(name = "payment_method")
  private String paymentMethod;

  @Column(name = "payment_type")
  private String paymentType;

  @ElementCollection
  @CollectionTable(name = "sub_pmts", joinColumns = @JoinColumn(name = "payment_notification_id"))
  private List<SubPmtsEntity> subPmts;

  @Column(name = "service_type")
  private String serviceType;

  @Column(name = "prepaid_cat")
  private String prepaidCat;

  @Column(name = "payer_id_type")
  private String payerIdType;

  @Column(name = "payer_id")
  private String payerId;

  @Column(name = "payer_nation")
  private String payerNation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public int getSdrCode() {
    return sdrCode;
  }

  public void setSdrCode(int sdrCode) {
    this.sdrCode = sdrCode;
  }

  public int getRcvCode() {
    return rcvCode;
  }

  public void setRcvCode(int rcvCode) {
    this.rcvCode = rcvCode;
  }

  public String getReqType() {
    return reqType;
  }

  public void setReqType(String reqType) {
    this.reqType = reqType;
  }

  public String getBillingNo() {
    return billingNo;
  }

  public void setBillingNo(String billingNo) {
    this.billingNo = billingNo;
  }

  public String getBillNo() {
    return billNo;
  }

  public void setBillNo(String billNo) {
    this.billNo = billNo;
  }

  public String getJoeBppsTrx() {
    return joeBppsTrx;
  }

  public void setJoeBppsTrx(String joeBppsTrx) {
    this.joeBppsTrx = joeBppsTrx;
  }

  public String getBankTrxId() {
    return bankTrxId;
  }

  public void setBankTrxId(String bankTrxId) {
    this.bankTrxId = bankTrxId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getPmtStatus() {
    return pmtStatus;
  }

  public void setPmtStatus(String pmtStatus) {
    this.pmtStatus = pmtStatus;
  }

  public String getDueAmt() {
    return dueAmt;
  }

  public void setDueAmt(String dueAmt) {
    this.dueAmt = dueAmt;
  }

  public String getPaidAmt() {
    return paidAmt;
  }

  public void setPaidAmt(String paidAmt) {
    this.paidAmt = paidAmt;
  }

  public String getFeesAmt() {
    return feesAmt;
  }

  public void setFeesAmt(String feesAmt) {
    this.feesAmt = feesAmt;
  }

  public boolean isFeesOnBiller() {
    return feesOnBiller;
  }

  public void setFeesOnBiller(boolean feesOnBiller) {
    this.feesOnBiller = feesOnBiller;
  }

  public String getProcessDate() {
    return processDate;
  }

  public void setProcessDate(String processDate) {
    this.processDate = processDate;
  }

  public String getStmtDate() {
    return stmtDate;
  }

  public void setStmtDate(String stmtDate) {
    this.stmtDate = stmtDate;
  }

  public String getAccessChannel() {
    return accessChannel;
  }

  public void setAccessChannel(String accessChannel) {
    this.accessChannel = accessChannel;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public List<SubPmtsEntity> getSubPmts() {
    return subPmts;
  }

  public void setSubPmts(List<SubPmtsEntity> subPmts) {
    this.subPmts = subPmts;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public String getPrepaidCat() {
    return prepaidCat;
  }

  public void setPrepaidCat(String prepaidCat) {
    this.prepaidCat = prepaidCat;
  }

  public String getPayerIdType() {
    return payerIdType;
  }

  public void setPayerIdType(String payerIdType) {
    this.payerIdType = payerIdType;
  }

  public String getPayerId() {
    return payerId;
  }

  public void setPayerId(String payerId) {
    this.payerId = payerId;
  }

  public String getPayerNation() {
    return payerNation;
  }

  public void setPayerNation(String payerNation) {
    this.payerNation = payerNation;
  }
}
