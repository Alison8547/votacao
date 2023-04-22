package com.br.api.votacao.service;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.domain.VotingSession;
import com.br.api.votacao.dto.request.VotingSessionRequest;
import com.br.api.votacao.dto.response.VotingSessionResponse;
import com.br.api.votacao.exception.BusinessException;
import com.br.api.votacao.mapper.VotingSessionMapper;
import com.br.api.votacao.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;
    private final PautaServiceImpl pautaService;
    private final VotingSessionMapper sessionMapper;
    private static final Integer DEFAULT_TIME_VOTING = 60;

    @Override
    public VotingSessionResponse openSession(Integer idPauta, VotingSessionRequest votingSessionRequest) {

        Pauta pauta = pautaService.findByIdPauta(idPauta);

        if (votingSessionRepository.findByPauta(pauta).isPresent()) {
            throw new BusinessException("Sessão de votação já existe!");
        }

        VotingSession votingSession = sessionMapper.toVotingSession(votingSessionRequest);
        votingSession.setDateOpen(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        votingSession.setPauta(pauta);

        if (votingSession.getDateClosing() == null) {
            votingSession.setDateClosing(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusSeconds(DEFAULT_TIME_VOTING));
            log.info("Tempo padrão na sessão foi ativada!");
        }


        if (votingSession.getDateClosing().isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))) {
            throw new BusinessException("Data de fechamento não pode ser antes da Data de abertura da Sessão!");
        }

        votingSessionRepository.save(votingSession);
        log.info("Sessão criada com sucesso!");

        return sessionMapper.toVotingSessionResponse(votingSession);
    }

    public VotingSession getVotingSession(Pauta pauta) {
        return votingSessionRepository.findByPauta(pauta)
                .orElseThrow(() -> new BusinessException("Sessão e Pauta não encontrada!"));
    }

}
