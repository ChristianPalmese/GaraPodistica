package com.example.garaPodistica.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GaraDTO {
    private int id;
    @NotBlank
    private String codice;
    @NotBlank
    private String descrizione;
    @NotNull
    private Integer lunghezzaKm;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSvolgimento;


}
