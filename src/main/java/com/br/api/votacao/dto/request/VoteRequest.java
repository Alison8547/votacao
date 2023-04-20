package com.br.api.votacao.dto.request;

import com.br.api.votacao.domain.enums.MessageVote;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {

    @NotBlank
    private String cpfAssociado;

    @NotNull
    private MessageVote messageVote;


}
