package com.br.api.votacao.repository;

import com.br.api.votacao.domain.Pauta;
import com.br.api.votacao.domain.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {

    Optional<VotingSession> findByPauta(Pauta pauta);
}
