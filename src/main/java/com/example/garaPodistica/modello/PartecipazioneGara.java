package com.example.garaPodistica.modello;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PartecipazioneGara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Atleta atleta;

    @ManyToOne
    private Gara gara;

    private LocalDateTime orarioArrivo;

    public PartecipazioneGara(Atleta atleta, Gara gara, LocalDateTime orarioArrivo) {
        this.atleta = atleta;
        this.gara = gara;
        this.orarioArrivo = orarioArrivo;
    }
}
