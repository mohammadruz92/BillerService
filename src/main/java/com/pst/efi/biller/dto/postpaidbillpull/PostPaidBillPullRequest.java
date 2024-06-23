package com.pst.efi.biller.dto.postpaidbillpull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class PostPaidBillPullRequest {
  @Valid
  @JsonProperty("mfep")
  public MFEP mfep;

  public record MFEP(MsgHeader msgHeader, MsgBody msgBody) {
    public record MsgHeader(String tmStp, TrsInf trsInf, String guid) {
      public record TrsInf(int sdrCode, int rcvCode, String reqTyp) {}
    }

    public record MsgBody(
        AcctInfo acctInfo,
        @NotBlank(message = "ServiceType cannot be blank")
            @Pattern(
                regexp = "^[\\d|\\w](.*\\S)*[\\d|\\w]*$",
                message = "ServiceType must match the pattern ^[\\d|\\w](.*\\S)*[\\d|\\w]*$")
            @Size(
                min = 1,
                max = 25,
                message = "ServiceType length must be between 1 and 25 characters")
            String serviceType) {
      public record AcctInfo(String billingNo, String billNo) {}
    }
  }
}
