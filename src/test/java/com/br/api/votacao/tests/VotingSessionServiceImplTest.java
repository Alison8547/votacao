package com.br.api.votacao.tests;

import com.br.api.votacao.builders.PautaBuilder;
import com.br.api.votacao.builders.VotingSessionBuilder;
import com.br.api.votacao.dto.response.VotingSessionResponse;
import com.br.api.votacao.exception.BusinessException;
import com.br.api.votacao.mapper.VotingSessionMapper;
import com.br.api.votacao.repository.VotingSessionRepository;
import com.br.api.votacao.service.PautaServiceImpl;
import com.br.api.votacao.service.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private VotingSessionMapper votingSessionMapper;


    @Test
    public void testMustCreateVotingSessionWithSuccess() {
        //(SETUP)
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        // when(votingSessionRepository.findByPauta(null)).thenReturn(Optional.of(VotingSessionBuilder.newVotingSessionEntity()));
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionRepository.save(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionMapper.toVotingSessionResponse(any())).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);

    }

    @Test(expected = BusinessException.class)
    public void testMustCreateVotingSessionAlreadyExistsWithError() {
        //(SETUP)
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());
        when(votingSessionRepository.findByPauta(any())).thenReturn(Optional.of(VotingSessionBuilder.newVotingSessionEntity()));

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(1, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNull(votingSessionOpen);

    }

    @Test
    public void testMustCreateVotingSessionWithDefaultTime() {
        //(SETUP)
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityDefaultTime());
        when(votingSessionRepository.save(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionMapper.toVotingSessionResponse(any())).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);

    }

    @Test(expected = BusinessException.class)
    public void testMustCreateVotingSessioBeforeTimeDataOpenError() {
        //(SETUP)
        when(pautaService.findByIdPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBeforeDataOpen());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);
    }

}
