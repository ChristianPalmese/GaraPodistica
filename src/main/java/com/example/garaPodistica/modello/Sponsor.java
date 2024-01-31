package com.example.garaPodistica.modello;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (length = 50)
    private String nome;
    private String tipologia;
    private boolean italiano;

    @ManyToMany(mappedBy = "sponsorList")
    private List<Atleta> atleti;

    public Sponsor(String nome, String tipologia, boolean italiano) {
        this.nome = nome;
        this.tipologia = tipologia;
        this.italiano = italiano;
    }
}

