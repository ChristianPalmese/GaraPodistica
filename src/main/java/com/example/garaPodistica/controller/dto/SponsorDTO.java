package com.example.garaPodistica.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorDTO {
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String tipologia;
    @NotNull
    private boolean italiano;
}
