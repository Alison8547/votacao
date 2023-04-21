package com.br.api.votacao.controller;

import com.br.api.votacao.dto.request.VotingSessionRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Validated
@RequestMapping("/voting-session")
public interface VotingSessionController {

    @Operation(summary = "Criar uma Sessão", description = "Salvar uma Sessão no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Criou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/create/{idPauta}")
    ResponseEntity<VotingSessionResponse> create(@PathVariable(name = "idPauta") Integer idPauta, @Valid @RequestBody VotingSessionRequest votingSessionRequest);
}
