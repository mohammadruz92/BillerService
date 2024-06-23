package com.pst.efi.biller.mapper;

import com.pst.efi.biller.domain.entity.PaymentNotificationEntity;
import com.pst.efi.biller.domain.entity.SubPmtsEntity;
import com.pst.efi.biller.dto.notification.PaymentNotificationRequest;
import com.pst.efi.biller.dto.notification.PaymentNotificationResponse;

import java.util.ArrayList;
import java.util.List;

public class PaymentNotificationMapper {

  public static PaymentNotificationEntity toEntity(
      PaymentNotificationRequest paymentNotificationRequest) {
    PaymentNotificationEntity entity = new PaymentNotificationEntity();
    PaymentNotificationRequest.MFEP mfep = paymentNotificationRequest.MFEP();
    PaymentNotificationRequest.MFEP.MsgHeader msgHeader = mfep.MsgHeader();
    PaymentNotificationRequest.MFEP.MsgBody msgBody = mfep.MsgBody();

    entity.setTimestamp(msgHeader.TmStp());
    entity.setGuid(msgHeader.GUID());
    entity.setSdrCode(msgHeader.TrsInf().SdrCode());
    entity.setRcvCode(msgHeader.TrsInf().RcvCode());
    entity.setReqType(msgHeader.TrsInf().ReqTyp());
    entity.setBillingNo(msgBody.BillingInfo().AcctInfo().BillingNo());
    entity.setBillNo(msgBody.BillingInfo().AcctInfo().BillNo());
    entity.setJoeBppsTrx(msgBody.BillingInfo().JOEBPPSTrx());
    entity.setBankTrxId(msgBody.BillingInfo().BankTrxId());
    entity.setBankCode(msgBody.BillingInfo().BankCode());
    entity.setPmtStatus(msgBody.BillingInfo().PmtStatus());
    entity.setDueAmt(msgBody.BillingInfo().DueAmt());
    entity.setPaidAmt(msgBody.BillingInfo().PaidAmt());
    entity.setFeesAmt(msgBody.BillingInfo().FeesAmt());
    entity.setFeesOnBiller(msgBody.BillingInfo().FeesOnBiller());
    entity.setProcessDate(msgBody.BillingInfo().ProcessDate());
    entity.setStmtDate(msgBody.BillingInfo().StmtDate());
    entity.setAccessChannel(msgBody.BillingInfo().AccessChannel());
    entity.setPaymentMethod(msgBody.BillingInfo().PaymentMethod());
    entity.setPaymentType(msgBody.BillingInfo().PaymentType());
    entity.setSubPmts(mapSubPmtsEntities(msgBody.BillingInfo().SubPmts()));
    entity.setServiceType(msgBody.BillingInfo().ServiceTypeDetails().ServiceType());
    entity.setPrepaidCat(msgBody.BillingInfo().ServiceTypeDetails().PrepaidCat());
    entity.setPayerIdType(msgBody.BillingInfo().PayerInfo().IdType());
    entity.setPayerId(msgBody.BillingInfo().PayerInfo().Id());
    entity.setPayerNation(msgBody.BillingInfo().PayerInfo().Nation());

    return entity;
  }

  private static List<SubPmtsEntity> mapSubPmtsEntities(
      PaymentNotificationRequest.MFEP.MsgBody.BillingInfo.SubPmts[] subPmts) {
    List<SubPmtsEntity> subPmtsEntities = new ArrayList<>();

    for (PaymentNotificationRequest.MFEP.MsgBody.BillingInfo.SubPmts subPmt : subPmts) {
      SubPmtsEntity subPmtsEntity = new SubPmtsEntity();
      subPmtsEntity.setAmount(subPmt.Amount());
      subPmtsEntity.setSetBnkCode(subPmt.SetBnkCode());
      subPmtsEntity.setAcctNo(subPmt.AcctNo());
      subPmtsEntities.add(subPmtsEntity);
    }

    return subPmtsEntities;
  }

  public static PaymentNotificationResponse toResponse(
      PaymentNotificationRequest paymentNotificationRequest) {
    PaymentNotificationRequest.MFEP mfepRequest = paymentNotificationRequest.MFEP();

    // Map MsgHeader
    PaymentNotificationResponse.MFEP.MsgHeader msgHeaderResponse =
        new PaymentNotificationResponse.MFEP.MsgHeader(
            mfepRequest.MsgHeader().TmStp(),
            mfepRequest.MsgHeader().GUID(),
            new PaymentNotificationResponse.MFEP.MsgHeader.TrsInf(
                mfepRequest.MsgHeader().TrsInf().SdrCode(),
                "RESPONSE_TYPE" // Replace with the appropriate response type
                ),
            new PaymentNotificationResponse.MFEP.MsgHeader.Result(
                0, // Replace with the appropriate error code
                "", // Replace with the appropriate error description
                PaymentNotificationResponse.SeverityCodes
                    .INFO // Replace with the appropriate severity code
                ));

    // Map MsgBody
    PaymentNotificationResponse.MFEP.MsgBody msgBodyResponse =
        new PaymentNotificationResponse.MFEP.MsgBody(
            new PaymentNotificationResponse.MFEP.MsgBody.Transactions(
                new PaymentNotificationResponse.MFEP.MsgBody.Transactions.TrxInf(
                    mfepRequest.MsgBody().BillingInfo().JOEBPPSTrx(),
                    mfepRequest.MsgBody().BillingInfo().ProcessDate(),
                    mfepRequest.MsgBody().BillingInfo().StmtDate(),
                    new PaymentNotificationResponse.MFEP.MsgBody.Transactions.TrxInf.Result(
                        0, // Replace with the appropriate error code
                        "", // Replace with the appropriate error description
                        PaymentNotificationResponse.SeverityCodes
                            .INFO // Replace with the appropriate severity code
                        ))));

    // Map MFEP
    PaymentNotificationResponse.MFEP mfepResponse =
        new PaymentNotificationResponse.MFEP(msgHeaderResponse, msgBodyResponse);

    // Map PaymentNotificationResponse
    return new PaymentNotificationResponse(mfepResponse);
  }
}
