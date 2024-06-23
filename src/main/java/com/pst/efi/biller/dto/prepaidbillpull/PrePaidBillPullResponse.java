package com.pst.efi.biller.dto.prepaidbillpull;

public record PrePaidBillPullResponse(MFEP MFEP) {
  public record MFEP(MsgHeader MsgHeader, MsgBody MsgBody) {
    public record MsgHeader(String TmStp, String GUID, TrsInf TrsInf, Result Result) {
      public record TrsInf(int SdrCode, String ResTyp) {}

      public record Result(int ErrorCode, String ErrorDesc, String Severity) {}
    }

    public record MsgBody(BillingInfo BillingInfo) {
      public record BillingInfo(
          Result Result,
          AcctInfo AcctInfo,
          String DueAmt,
          String ValidationCode,
          ServiceTypeDetails ServiceTypeDetails,
          SubPmts[] SubPmts,
          AdditionalInfo AdditionalInfo) {
        public record Result(int ErrorCode, String ErrorDesc, String Severity) {}

        public record AcctInfo(String BillingNo, int BillerCode) {}

        public record ServiceTypeDetails(String ServiceType, String PrepaidCat) {}

        public record SubPmts(String Amount, int SetBnkCode, String AcctNo) {}

        public record AdditionalInfo(
            String CustName, String FreeText, String Email, String Phone) {}
      }
    }
  }
}
