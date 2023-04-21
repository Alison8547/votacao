package com.br.api.votacao.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotingSessionRequest {

    private LocalDateTime dateClosing;
}
