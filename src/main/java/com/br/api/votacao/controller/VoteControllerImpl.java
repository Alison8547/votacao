package com.br.api.votacao.controller;

import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.ValidResponse;
import com.br.api.votacao.dto.response.VoteResponse;
import com.br.api.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VoteService voteService;

    @Override
    public ResponseEntity<VoteResponse> create(Integer idPauta, VoteRequest voteRequest) {
        return new ResponseEntity<>(voteService.vote(idPauta, voteRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ValidResponse> validCpf(String cpf) {
        return new ResponseEntity<>(voteService.sendCpf(cpf), HttpStatus.OK);
    }
}
