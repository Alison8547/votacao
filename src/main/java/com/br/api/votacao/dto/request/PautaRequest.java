package com.br.api.votacao.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {

    @NotBlank
    @Schema(description = "Alguma pauta para votar",example = "pauta lei 7.9.23")
    private String pauta;
}
