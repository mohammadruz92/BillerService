package com.pst.efi.biller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullResponse;
import com.pst.efi.biller.service.postpaid.billpull.PostPaidBillPullServiceImpl;
import com.pst.efi.biller.jwt.SecurityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postpaid")
public class PostPaidBillPullController {

  private final PostPaidBillPullServiceImpl service;

  public PostPaidBillPullController(PostPaidBillPullServiceImpl service) {
    this.service = service;
  }

  @PostMapping(
      value = "/billPull",
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  @PreAuthorize(SecurityConstants.MODERATOR)
  public ResponseEntity<PostPaidBillPullResponse> post(@RequestBody PostPaidBillPullRequest request)
      throws JsonProcessingException {
    return ResponseEntity.ok(service.postPaidBillPull(request));
  }
}
