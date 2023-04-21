package com.br.api.votacao.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class ResultRequest {
    private String pauta;
    private Map<String,Long> resultado;
}
