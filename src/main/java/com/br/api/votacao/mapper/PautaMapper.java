package com.br.api.votacao.mapper;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PautaMapper {

    private final ModelMapper modelMapper;

    public Pauta toPauta(PautaRequest pautaRequest) {
        return modelMapper.map(pautaRequest, Pauta.class);
    }

    public PautaResponse toPautaResponse(Pauta pauta) {
        return modelMapper.map(pauta, PautaResponse.class);
    }
}
