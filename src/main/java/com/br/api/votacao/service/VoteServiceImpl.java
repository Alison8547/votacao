package com.br.api.votacao.service;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.domain.VotingSession;
import com.br.api.votacao.domain.enums.MessageVote;
import com.br.api.votacao.dto.request.VoteRequest;
import com.br.api.votacao.dto.response.ResultResponse;
import com.br.api.votacao.dto.response.VoteResponse;
import com.br.api.votacao.exception.BusinessException;
import com.br.api.votacao.mapper.VoteMapper;
import com.br.api.votacao.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final VotingSessionServiceImpl votingSessionService;
    private final PautaServiceImpl pautaService;

    @Override
    public VoteResponse vote(Integer idPauta, VoteRequest voteRequest) {
        VotingSession votingSession = votingSessionService.getVotingSession(pautaService.findByIdPauta(idPauta));

        if (LocalDateTime.now().isAfter(votingSession.getDateClosing())) {
            throw new BusinessException("Sessão de votação já está fechada!");
        }

        Vote vote = voteMapper.toVote(voteRequest);

        if (voteRequest.getMessageVote().equalsIgnoreCase("Sim")) {
            vote.setMessageVote(MessageVote.SIM);
        } else if (voteRequest.getMessageVote().equalsIgnoreCase("Não")) {
            vote.setMessageVote(MessageVote.NAO);
        } else {
            throw new BusinessException("Valor digitado incorreto! vote Sim ou Não");
        }
        vote.setVotingSession(votingSession);
        vote.setDateVote(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        if (voteRepository.existsByVotingSessionAndCpfAssociado(votingSession, vote.getCpfAssociado())) {
            throw new BusinessException("CPF Associado já tem registrado voto nessa pauta!");
        }

        voteRepository.save(vote);
        log.info("Voto salvo com sucesso!");

        return voteMapper.toVoteResponse(vote);
    }


    @Override
    public ResultResponse resultVoting(Integer idPauta) {
        Set<Vote> votesList = votingSessionService.getVotingSession(pautaService.findByIdPauta(idPauta)).getVotes();
        Pauta pauta = pautaService.findByIdPauta(idPauta);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("SIM", votesList.stream().filter(x -> x.getMessageVote().toString().equalsIgnoreCase("SIM")).count());
        resultMap.put("NAO", votesList.stream().filter(x -> x.getMessageVote().toString().equalsIgnoreCase("NAO")).count());

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setPauta(pauta.getPauta());
        resultResponse.setResultado(resultMap);

        return resultResponse;
    }


}
