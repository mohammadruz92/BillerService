package com.pst.efi.biller.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pst.efi.biller.domain.entity.TransactionEntity;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullRequest;
import com.pst.efi.biller.util.Util;

import java.sql.Timestamp;

public class BillPullMapper {

  public static TransactionEntity toEntity(PostPaidBillPullRequest request)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TransactionEntity transaction = new TransactionEntity();
    transaction.setGuid(Util.getTimeSTampId());
    transaction.setRequest(objectMapper.writeValueAsString(request));
    transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    return transaction;
  }

  public static TransactionEntity toEntity(PrePaidBillPullRequest request)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TransactionEntity transaction = new TransactionEntity();
    transaction.setGuid(Util.getTimeSTampId());
    transaction.setRequest(objectMapper.writeValueAsString(request));
    transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    return transaction;
  }
}
