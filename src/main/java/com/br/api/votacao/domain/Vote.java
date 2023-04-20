package com.br.api.votacao.domain;

import com.br.api.votacao.domain.enums.MessageVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class Vote implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String cpfAssociado;

    @Column(name = "date_vote")
    private LocalDateTime dateVote;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_vote")
    private MessageVote messageVote;


    @ManyToOne
    @JoinColumn(name = "id_voting_session")
    private VotingSession votingSession;
}
