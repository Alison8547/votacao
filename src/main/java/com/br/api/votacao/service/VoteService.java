package com.br.api.votacao.service;

import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.ResultResponse;
import com.br.api.votacao.dto.response.VoteResponse;

import javax.transaction.Transactional;

public interface VoteService {
    @Transactional
    VoteResponse vote(Integer idPauta, VoteRequest voteRequest);

    ResultResponse resultVoting(Integer idpauta);

    void sendKafkaResultVoting(Integer idpauta);
}
