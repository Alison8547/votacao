package com.br.api.votacao.builders;

import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.domain.enums.MessageVote;
import com.br.api.votacao.dto.request.VoteRequest;

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


    public static VoteRequest newVoteRequest() {
        return VoteRequest.builder()
                .cpfAssociado("943.657.200-07")
                .messageVote("Sim")
                .build();
    }

    public static VoteRequest newVoteNewCpfRequest() {
        return VoteRequest.builder()
                .cpfAssociado("501.980.140-23")
                .messageVote("Sim")
                .build();
    }

    public static VoteRequest newVoteNoRequest() {
        return VoteRequest.builder()
                .cpfAssociado("943.657.200-07")
                .messageVote("Não")
                .build();
    }

    public static VoteRequest newVoteValidMessageRequest() {
        return VoteRequest.builder()
                .cpfAssociado("943.657.200-07")
                .messageVote("outro valor")
                .build();
    }
}
