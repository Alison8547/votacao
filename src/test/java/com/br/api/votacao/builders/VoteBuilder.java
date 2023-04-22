package com.br.api.votacao.builders;

import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.domain.enums.MessageVote;
import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.VoteResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VoteBuilder {


    public static Vote newVoteEntity() {
        return Vote.builder()
                .votingSession(VotingSessionBuilder.newVotingSessionEntity())
                .dateVote(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .cpfAssociado("943.657.200-07")
                .messageVote(MessageVote.SIM)
                .build();

    }

    public static VoteResponse newVoteResponse() {
        return VoteResponse.builder()
                .messageVote(MessageVote.SIM)
                .dateVote(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .build();
    }

    public static VoteRequest newVoteRequest() {
        return VoteRequest.builder()
                .cpfAssociado("943.657.200-07")
                .messageVote("Sim")
                .build();
    }
}
