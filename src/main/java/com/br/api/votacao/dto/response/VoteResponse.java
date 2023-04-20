package com.br.api.votacao.dto.response;

import com.br.api.votacao.domain.enums.MessageVote;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteResponse {

    private LocalDateTime dateVote;
    private MessageVote messageVote;
}
