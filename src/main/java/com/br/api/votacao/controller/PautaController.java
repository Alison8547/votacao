package com.br.api.votacao.controller;

import com.br.api.votacao.dto.request.PautaRequest;
import com.br.api.votacao.dto.response.PautaResponse;
import com.br.api.votacao.dto.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/pauta")
public interface PautaController {
    @Operation(summary = "Criar uma pauta", description = "Salvar uma pauta no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Criou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/create")
    ResponseEntity<PautaResponse> create(@Valid @RequestBody PautaRequest pautaRequest);

    @Operation(summary = "Pegar o resultado da votação da pauta", description = "Pega o resultado da votação da pauta no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resgatou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/result/{idPauta}")
    ResponseEntity<ResultResponse> resultVoting(@PathVariable(name = "idPauta") Integer idPauta);
}
