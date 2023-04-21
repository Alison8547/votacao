package com.br.api.votacao.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {

    @NotBlank
    private String cpfAssociado;

    @NotNull
    private String messageVote;


}
