package com.br.api.votacao.service;

import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;

import javax.transaction.Transactional;

public interface PautaService {

    @Transactional
    PautaResponse createPauta(PautaRequest pautaRequest);
}
