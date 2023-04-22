package com.br.api.votacao.builders;

import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.domain.VotingSession;
import com.br.api.votacao.dto.request.VotingSessionRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VotingSessionBuilder {
    private static final Set<Vote> votes = new HashSet<>(List.of(VoteBuilder.newVoteEntity()));

    public static VotingSession newVotingSessionEntity() {
        return VotingSession.builder()
                .idVotingSession(2)
                .dateOpen(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .dateClosing(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(3))
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSession newVotingSessionEntityBeforeDataOpen() {
        return VotingSession.builder()
                .idVotingSession(2)
                .dateOpen(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .dateClosing(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).minusHours(2))
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSession newVotingSessionEntityDefaultTime() {
        return VotingSession.builder()
                .idVotingSession(2)
                .dateOpen(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .dateClosing(null)
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSessionResponse newVotingSessionResponse() {
        return VotingSessionResponse.builder()
                .idVotingSession(2)
                .pautaVoting(PautaBuilder.newPautaEntity())
                .dateOpen(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .dateClosing(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(3))
                .build();
    }

    public static VotingSessionRequest newVotingSessionRequest() {
        return VotingSessionRequest.builder()
                .dateClosing(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(3))
                .build();
    }
}
