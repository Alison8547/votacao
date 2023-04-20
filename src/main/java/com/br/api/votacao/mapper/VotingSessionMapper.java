package com.br.api.votacao.mapper;

import com.br.api.votacao.domain.VotingSession;
import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotingSessionMapper {

    private final ModelMapper modelMapper;

    public VotingSession toVotingSession(VoteRequest voteRequest) {
        return modelMapper.map(voteRequest, VotingSession.class);
    }

    public VotingSessionResponse toVotingSessionResponse(VotingSession votingSession) {
        return modelMapper.map(votingSession, VotingSessionResponse.class);
    }
}
