package com.br.api.votacao.repository;

import com.br.api.votacao.domain.Vote;
import com.br.api.votacao.domain.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    Boolean existsByVotingSessionAndCpfAssociado(VotingSession votingSession, String cpfAssociado);
}
