package com.br.api.votacao.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionRequest {
    @Schema(description = "Data do fechamento da sessão")
    private LocalDateTime dateClosing;
}
