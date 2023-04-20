package com.br.api.votacao.dto.request;

import com.br.api.votacao.domain.Pauta;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VotingSessionRequest {

    private LocalDateTime dateClosing;

    @NotNull
    private Pauta pauta;

}
