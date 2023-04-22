package com.br.api.votacao.dto.response;

import com.br.api.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionResponse {

    private Integer idVotingSession;
    private LocalDateTime dateOpen;
    private LocalDateTime dateClosing;
    private Pauta pautaVoting;
}
