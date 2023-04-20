package com.br.api.votacao.mapper;

import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteMapper {

    private final ModelMapper modelMapper;

    public Vote toVote(VoteRequest voteRequest) {
        return modelMapper.map(voteRequest, Vote.class);
    }

    public VoteResponse toVoteResponse(Vote vote) {
        return modelMapper.map(vote, VoteResponse.class);
    }


}
