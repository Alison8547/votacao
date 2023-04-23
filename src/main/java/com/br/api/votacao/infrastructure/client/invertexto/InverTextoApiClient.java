package com.br.api.votacao.infrastructure.client.invertexto;

import com.br.api.votacao.dto.response.ValidResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inver-texto-api", url = "${application.client.inver-texto-api.url}")
public interface InverTextoApiClient {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ValidResponse validCpf(@RequestParam(name = "token") String token, @RequestParam(name = "value") String value, @RequestParam(name = "type") String type);

}
