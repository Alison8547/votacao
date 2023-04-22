package com.br.api.votacao.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotingSessionRequest {
    @Schema(description = "Data do fechamento da sessão")
    private LocalDateTime dateClosing;
}
