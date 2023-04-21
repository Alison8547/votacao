package com.br.api.votacao.controller;

import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;
import com.br.api.votacao.service.PautaService;
import com.br.api.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PautaControllerImpl implements PautaController {

    private final PautaService pautaService;
    private final VoteService voteService;


    @Override
    public ResponseEntity<PautaResponse> create(PautaRequest pautaRequest) {
        return new ResponseEntity<>(pautaService.createPauta(pautaRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Long>> resultVoting(Integer idPauta) {
        return new ResponseEntity<>(voteService.resultVoting(idPauta), HttpStatus.OK);
    }


}
