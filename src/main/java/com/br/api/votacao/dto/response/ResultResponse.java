package com.br.api.votacao.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class ResultResponse {
    private String pauta;
    private Map<String,Long> resultado;
}
