package com.example.garaPodistica.controller.dto;

import com.example.garaPodistica.modello.Atleta;
import com.example.garaPodistica.modello.Gara;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartecipazioneGaraDTO {
    private int id;
    @NotNull
    private Integer atletaID;
    @NotNull
    private Integer garaID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orarioArrivo;

}
