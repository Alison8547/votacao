package com.br.api.votacao.service;

import com.br.api.votacao.dto.request.VotingSessionRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;

import javax.transaction.Transactional;

public interface VotingSessionService {
    @Transactional
    VotingSessionResponse openSession(Integer idPauta, VotingSessionRequest votingSessionRequest);
}
