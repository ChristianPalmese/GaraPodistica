package com.example.garaPodistica.modello;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Gara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (length = 50)
    private String codice;
    private String descrizione;
    private int lunghezzaKm;
    private LocalDate dataSvolgimento;


    @OneToMany(mappedBy = "gara")
    private List<PartecipazioneGara> partecipanti;

    public Gara(String codice, String descrizione, int lunghezzaKm, LocalDate dataSvolgimento) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.lunghezzaKm = lunghezzaKm;
        this.dataSvolgimento = dataSvolgimento;
    }
}
