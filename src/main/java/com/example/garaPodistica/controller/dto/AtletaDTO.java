package com.example.garaPodistica.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtletaDTO {
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank(message = "citta obbligatoria")
    private String citta;
}
