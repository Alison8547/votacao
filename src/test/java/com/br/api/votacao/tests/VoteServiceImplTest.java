package com.br.api.votacao.tests;

import com.br.api.votacao.builders.PautaBuilder;
import com.br.api.votacao.builders.VoteBuilder;
import com.br.api.votacao.builders.VotingSessionBuilder;
import com.br.api.votacao.dto.response.ResultResponse;
import com.br.api.votacao.dto.response.ValidResponse;
import com.br.api.votacao.dto.response.VoteResponse;
import com.br.api.votacao.exception.BusinessException;
import com.br.api.votacao.infrastructure.client.invertexto.InverTextoApiClient;
import com.br.api.votacao.infrastructure.client.resultkafka.ResultKafkaApiClient;
import com.br.api.votacao.mapper.ResultMapper;
import com.br.api.votacao.mapper.VoteMapper;
import com.br.api.votacao.repository.VoteRepository;
import com.br.api.votacao.service.PautaServiceImpl;
import com.br.api.votacao.service.VoteServiceImpl;
import com.br.api.votacao.service.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private ResultKafkaApiClient resultKafkaApiClient;

    @Mock
    private InverTextoApiClient inverTextoApiClient;

    @Mock
    private ResultMapper resultMapper;


    @Test
    public void testMustVoteWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.save(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.existsByVotingSessionAndCpfAssociado(any(), any())).thenReturn(false);

        //(ACT)

        voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteNewCpfRequest());

        //(ASSERT)
        assertEquals("501.980.140-23", VoteBuilder.newVoteNewCpfRequest().getCpfAssociado());
        assertEquals("Sim", VoteBuilder.newVoteRequest().getMessageVote());

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteOffSessionWithError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBefore());

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteRequest());

        //(ASSERT)
        assertNull(vote);

    }

    @Test
    public void testMustVoteNoWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.save(any())).thenReturn(VoteBuilder.newVoteEntity());

        //(ACT)

        voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteNoRequest());

        //(ASSERT)
        assertEquals("943.657.200-07", VoteBuilder.newVoteNoRequest().getCpfAssociado());
        assertEquals("Não", VoteBuilder.newVoteNoRequest().getMessageVote());

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteValidMessageWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteValidMessageRequest());

        //(ASSERT)
        assertNull(vote);

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteCpfAlreadyExistsWithError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.existsByVotingSessionAndCpfAssociado(any(), any())).thenReturn(true);

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteRequest());

        //(ASSERT)
        assertNull(vote);
    }

    @Test
    public void testMustVoteResultSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBefore());
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());

        //(ACT)
        ResultResponse result = voteService.resultVoting(PautaBuilder.newPautaEntity().getIdPauta());
        List<Long> longList = new ArrayList<>();
        Set<Map.Entry<String, Long>> entries = result.getResultado().entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            longList.add(entry.getValue());
        }


        //(ASSERT)
        assertNotNull(result);
        assertEquals(PautaBuilder.newPautaEntity().getPauta(), result.getPauta());
        assertEquals(1, longList.get(0));
        assertEquals(0, longList.get(1));

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteResultSessionOpenError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());

        //(ACT)
        ResultResponse result = voteService.resultVoting(PautaBuilder.newPautaEntity().getIdPauta());


        //(ASSERT)
        assertNull(result);


    }

    @Test
    public void testMustSendKafkaResultVotingSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBefore());
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());

        //(ACT)
        ResultResponse result = voteService.resultVoting(PautaBuilder.newPautaEntity().getIdPauta());
        voteService.sendKafkaResultVoting(PautaBuilder.newPautaEntity().getIdPauta());
        List<Long> longList = new ArrayList<>();
        Set<Map.Entry<String, Long>> entries = result.getResultado().entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            longList.add(entry.getValue());
        }


        //(ASSERT)
        assertNotNull(result);
        assertEquals(PautaBuilder.newPautaEntity().getPauta(), result.getPauta());
        assertEquals(1, longList.get(0));
        assertEquals(0, longList.get(1));

    }

    @Test
    public void testMustSendValidCpfVoteSuccess() {
        //(SETUP)
        String cpf = "725.780.400-10";

        when(inverTextoApiClient.validCpf(any(), any(), any())).thenReturn(ValidResponse.builder().valid(true).build());

        //(ACT)
        ValidResponse validResponse = voteService.sendCpf(cpf);

        //(ASSERT)
        assertNotNull(validResponse);
        assertTrue(validResponse.isValid());

    }


}
