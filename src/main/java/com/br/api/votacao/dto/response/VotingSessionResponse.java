package com.br.api.votacao.dto.response;

import com.br.api.votacao.domain.Pauta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotingSessionResponse {

    private Integer idVotingSession;
    private LocalDateTime dateOpen;
    private LocalDateTime dateClosing;
    private Pauta pautaVoting;
}
