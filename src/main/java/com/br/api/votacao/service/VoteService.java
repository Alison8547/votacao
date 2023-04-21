package com.br.api.votacao.service;

import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.VoteResponse;

import javax.transaction.Transactional;
import java.util.Map;

public interface VoteService {
    @Transactional
    VoteResponse vote(Integer idPauta, VoteRequest voteRequest);

    Map<String, Long> resultVoting(Integer idpauta);
}
