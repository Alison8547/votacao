package com.br.api.votacao.infrastructure.client.resultkafka;

import com.br.api.votacao.dto.request.ResultRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "result-kafka-api", url = "${application.client.result-kafka-api.url}")
public interface ResultKafkaApiClient {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendResult(@RequestBody List<ResultRequest> resultRequests);
}
