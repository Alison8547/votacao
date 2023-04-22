package com.br.api.votacao.builders;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;

public class PautaBuilder {

    public static Pauta newPautaEntity() {
        return Pauta.builder()
                .idPauta(1)
                .pauta("pauta 2.4")
                .build();
    }


    public static Pauta newPautaEntityNoSession() {
        return Pauta.builder()
                .idPauta(7)
                .pauta("pauta 2.7")
                .build();
    }

    public static PautaResponse newPautaResponse() {
        return PautaResponse.builder()
                .idPauta(1)
                .pauta("pauta 2.4")
                .build();
    }

    public static PautaRequest newPautaRequest() {
        return PautaRequest.builder()
                .pauta("pauta 2.4")
                .build();
    }
}
