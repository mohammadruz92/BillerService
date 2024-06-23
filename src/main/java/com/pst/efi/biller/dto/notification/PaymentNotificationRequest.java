package com.pst.efi.biller.dto.notification;

public record PaymentNotificationRequest(MFEP MFEP) {

  public record MFEP(MsgHeader MsgHeader, MsgBody MsgBody) {
    public record MsgHeader(String TmStp, String GUID, TrsInf TrsInf) {
      public record TrsInf(int SdrCode, int RcvCode, String ReqTyp) {}
    }

    public record MsgBody(BillingInfo BillingInfo) {
      public record BillingInfo(
          AcctInfo AcctInfo,
          String JOEBPPSTrx,
          String BankTrxId,
          String BankCode,
          String PmtStatus,
          String DueAmt,
          String PaidAmt,
          String FeesAmt,
          boolean FeesOnBiller,
          String ProcessDate,
          String StmtDate,
          String AccessChannel,

          String PaymentMethod,
          String PaymentType,
          SubPmts[] SubPmts,
          ServiceTypeDetails ServiceTypeDetails,
          PayerInfo PayerInfo) {
        public record AcctInfo(String BillingNo, String BillNo) {}

        public record SubPmts(String Amount, int SetBnkCode, String AcctNo) {}

        public record ServiceTypeDetails(String ServiceType, String PrepaidCat) {}

        public record PayerInfo(String IdType, String Id, String Nation) {}
      }
    }
  }
}
