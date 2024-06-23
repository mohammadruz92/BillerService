package com.pst.efi.biller.service.postpaid.billpull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pst.efi.biller.domain.entity.TransactionEntity;
import com.pst.efi.biller.domain.repository.TransactionRepository;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullRequest;
import com.pst.efi.biller.dto.postpaidbillpull.PostPaidBillPullResponse;
import com.pst.efi.biller.mapper.BillPullMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class PostPaidBillPullServiceImpl implements PostPaidBillPullService {

  @Value("${provider.url}")
  private String providerUrl;

  private final WebClient webClient;
  private final TransactionRepository repository;
  private final Logger logger = LoggerFactory.getLogger(PostPaidBillPullServiceImpl.class);

  public PostPaidBillPullServiceImpl(WebClient webClient, TransactionRepository repository) {
    this.webClient = webClient;
    this.repository = repository;
  }

  @Override
  public PostPaidBillPullResponse postPaidBillPull(PostPaidBillPullRequest request)
      throws JsonProcessingException {

    TransactionEntity transactionEntity = repository.save(BillPullMapper.toEntity(request));

    HttpHeaders headers = createHeaders();
    ResponseEntity<PostPaidBillPullResponse> response = sendPostRequest(request, headers);

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

  private ResponseEntity<PostPaidBillPullResponse> sendPostRequest(
      PostPaidBillPullRequest request, HttpHeaders headers) {
    Mono<ResponseEntity<PostPaidBillPullResponse>> responseMono =
        webClient
            .method(HttpMethod.POST)
            .uri(providerUrl)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .toEntity(PostPaidBillPullResponse.class);

    return responseMono.block();
  }

  private void updateTransactionEntity(
      PostPaidBillPullResponse responseBody, TransactionEntity transactionEntity)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    repository.updateById(objectMapper.writeValueAsString(responseBody), transactionEntity.getId());
  }

  private void logResponse(ResponseEntity<PostPaidBillPullResponse> response) {
    logger.info("Response code: {}", response.getStatusCode());
    logger.info("Response body: {}", response.getBody());
  }
}
