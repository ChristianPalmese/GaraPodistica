package com.example.garaPodistica.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RigaTabellaPartecipazioneDTO {
    public String nomeAtleta;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    public LocalDateTime orarioArrivo;

}
