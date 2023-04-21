package com.br.api.votacao.service;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;
import com.br.api.votacao.exception.BusinessException;
import com.br.api.votacao.mapper.PautaMapper;
import com.br.api.votacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;


    @Override
    public PautaResponse createPauta(PautaRequest pautaRequest) {

        Pauta pauta = pautaMapper.toPauta(pautaRequest);
        pautaRepository.save(pauta);
        log.info("Pauta salvo com sucesso!");
        return pautaMapper.toPautaResponse(pauta);
    }

    public Pauta findByIdPauta(Integer idPauta) {
        return pautaRepository.findById(idPauta).orElseThrow(() -> new BusinessException("Pauta não encontrada!"));
    }
}
