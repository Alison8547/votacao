package com.br.api.votacao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voting_session")
public class VotingSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVotingSession;

    @Column(name = "date_open")
    private LocalDateTime dateOpen;

    @Column(name = "date_closing")
    private LocalDateTime dateClosing;


    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "votingSession", cascade = CascadeType.ALL)
    private Set<Vote> votes = new LinkedHashSet<>();


}
