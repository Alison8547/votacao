package com.br.api.votacao.dto.response;

import com.br.api.votacao.domain.enums.MessageVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {

    private LocalDateTime dateVote;
    private MessageVote messageVote;
}
