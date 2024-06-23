package com.pst.efi.biller.service.prepaid.billpull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullRequest;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullResponse;


public interface PrePaidBillPullService {

    public PrePaidBillPullResponse prePaidBillPull(PrePaidBillPullRequest request) throws JsonProcessingException;
}
