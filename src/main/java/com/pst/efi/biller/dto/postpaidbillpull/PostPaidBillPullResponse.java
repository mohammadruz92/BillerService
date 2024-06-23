package com.pst.efi.biller.dto.postpaidbillpull;

import java.util.List;

public record PostPaidBillPullResponse(MFEP MFEP) {
  public record MFEP(MsgHeader msgHeader, MsgBody msgBody) {}

  public record MsgHeader(String TmStp, String guid, TrsInf trsInf, Result result) {}

  public record TrsInf(int SdrCode, String ResTyp) {}

  public record Result(int ErrorCode, String ErrorDesc, String Severity) {}

  public record MsgBody(int RecCount, List<BillRec> BillsRec) {}

  public record BillRec(
      Result result,
      AcctInfo acctInfo,
      String BillStatus,
      String DueAmount,
      String IssueDate,
      String OpenDate,
      String DueDate,
      String ExpiryDate,
      String CloseDate,
      String ServiceType,
      String BillType,
      PmtConst PmtConst,
      List<SubPmt> SubPmts,
      int BillsCount,
      AdditionalInfo AdditionalInfo) {}

  public record AcctInfo(String BillingNo, String BillNo) {}

  public record PmtConst(boolean AllowPart, String Lower, String Upper) {}

  public record SubPmt(String Amount, int SetBnkCode, String AcctNo) {}

  public record AdditionalInfo(String CustName, String FreeText, String Email, String Phone) {}
}
