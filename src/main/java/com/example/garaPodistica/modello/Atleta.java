package com.example.garaPodistica.modello;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Atleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (length = 50)
    private String nome;
    @Column (length = 50)
    private String cognome;
    @Column (length = 50)
    private String citta;

    @ManyToMany
    @JoinTable(
            name = "atleta_sponsor",
            joinColumns = @JoinColumn(name = "atleta_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsor_id")
    )
    private List<Sponsor> sponsorList;

    @OneToMany(mappedBy = "atleta")
    private List<PartecipazioneGara> partecipazioni;

    public Atleta(String nome, String cognome, String citta) {
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
    }
}
