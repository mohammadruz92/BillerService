package com.pst.efi.biller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullRequest;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullResponse;
import com.pst.efi.biller.service.prepaid.billpull.PrePaidBillPullService;
import com.pst.efi.biller.jwt.SecurityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prepaid")
public class PrePaidBillPullController {

    private final PrePaidBillPullService service;

    public PrePaidBillPullController(PrePaidBillPullService service) {
        this.service = service;
    }

    @PostMapping(
            value = "/billPull",
            produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    @PreAuthorize(SecurityConstants.MODERATOR)
    public ResponseEntity<PrePaidBillPullResponse> post(@RequestBody PrePaidBillPullRequest request)
            throws JsonProcessingException {
        return ResponseEntity.ok(service.prePaidBillPull(request));
    }
}
