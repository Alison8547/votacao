package com.br.api.votacao.controller;

import com.br.api.votacao.dto.request.VotingSessionRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;
import com.br.api.votacao.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VotingSessionControllerImpl implements VotingSessionController {

    private final VotingSessionService votingSessionService;


    @Override
    public ResponseEntity<VotingSessionResponse> create(Integer idPauta, VotingSessionRequest votingSessionRequest) {
        return new ResponseEntity<>(votingSessionService.openSession(idPauta, votingSessionRequest), HttpStatus.CREATED);
    }
}
