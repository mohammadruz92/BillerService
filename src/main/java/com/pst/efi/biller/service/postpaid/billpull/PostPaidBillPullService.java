package com.pst.efi.biller.service.postpaid.billpull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullResponse;

public interface PostPaidBillPullService {

    public PostPaidBillPullResponse postPaidBillPull(PostPaidBillPullRequest request) throws JsonProcessingException;
}
