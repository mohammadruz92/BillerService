package com.pst.efi.biller.dto.prepaidbillpull;

public record PrePaidBillPullRequest(MFEP MFEP) {
  public record MFEP(MsgHeader MsgHeader, MsgBody MsgBody) {
    public record MsgHeader(String TmStp, TrsInf TrsInf, String GUID) {
      public record TrsInf(int SdrCode, int RcvCode, String ReqTyp) {}
    }

    public record MsgBody(BillingInfo BillingInfo) {
      public record BillingInfo(
          AcctInfo AcctInfo, ServiceTypeDetails ServiceTypeDetails, String ValidationCode) {
        public record AcctInfo(int BillerCode, String BillingNo) {}

        public record ServiceTypeDetails(String ServiceType, String PrepaidCat) {}
      }
    }
  }
}
