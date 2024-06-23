package com.pst.efi.biller.service.prepaid.billpull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pst.efi.biller.domain.entity.TransactionEntity;
import com.pst.efi.biller.domain.repository.TransactionRepository;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullResponse;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullRequest;
import com.pst.efi.biller.dto.prepaidbillpull.PrePaidBillPullResponse;
import com.pst.efi.biller.mapper.BillPullMapper;
import com.pst.efi.biller.service.postpaid.billpull.PostPaidBillPullServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class PrePaidBillPullServiceImpl implements PrePaidBillPullService {

  @Value("${provider.url}")
  private String providerUrl;

  private final WebClient webClient;
  private final TransactionRepository repository;

  private final Logger logger = LoggerFactory.getLogger(PrePaidBillPullServiceImpl.class);

  public PrePaidBillPullServiceImpl(WebClient webClient, TransactionRepository repository) {
    this.webClient = webClient;
    this.repository = repository;
  }

  @Override
  public PrePaidBillPullResponse prePaidBillPull(PrePaidBillPullRequest request) throws JsonProcessingException {

    TransactionEntity transactionEntity = repository.save(BillPullMapper.toEntity(request));

    HttpHeaders headers = createHeaders();
    ResponseEntity<PrePaidBillPullResponse> response = sendPostRequest(request, headers);

    updateTransactionEntity(response.getBody(), transactionEntity);

    logResponse(response);
    return response.getBody();
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

  private ResponseEntity<PrePaidBillPullResponse> sendPostRequest(
      PrePaidBillPullRequest request, HttpHeaders headers) {
    Mono<ResponseEntity<PrePaidBillPullResponse>> responseMono =
        webClient
            .method(HttpMethod.POST)
            .uri(providerUrl)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .toEntity(PrePaidBillPullResponse.class);

    return responseMono.block();
  }

  private void updateTransactionEntity(
      PrePaidBillPullResponse responseBody, TransactionEntity transactionEntity)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    repository.updateById(objectMapper.writeValueAsString(responseBody), transactionEntity.getId());
  }

  private void logResponse(ResponseEntity<PrePaidBillPullResponse> response) {
    logger.info("Response code: {}", response.getStatusCode());
    logger.info("Response body: {}", response.getBody());
  }
}
