package com.br.api.votacao.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @NotBlank
    @CPF(message = "Cpf inválido para votar!")
    @Schema(description = "Cpf do associado para votar",example = "390.434.850-71")
    private String cpfAssociado;

    @NotNull
    @Schema(description = "Voto Sim ou Não",example = "Não")
    private String messageVote;


}
