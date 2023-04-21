package com.br.api.votacao.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {

    @NotBlank
    @CPF(message = "Cpf inválido para votar!")
    private String cpfAssociado;

    @NotNull
    private String messageVote;


}
