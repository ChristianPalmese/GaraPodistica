package com.example.garaPodistica.controller;

import com.example.garaPodistica.controller.dto.GaraDTO;
import com.example.garaPodistica.controller.dto.PartecipazioneGaraDTO;
import com.example.garaPodistica.controller.dto.RigaTabellaPartecipazioneDTO;
import com.example.garaPodistica.service.PartecipazioneGaraService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PartecipazioneGaraController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */
    @Autowired
    PartecipazioneGaraService partecipazioneGaraService;

    @GetMapping("/gara/{id}/partecipazioni")
    @ResponseBody
    public List<RigaTabellaPartecipazioneDTO> findGaraWithTabella(@PathVariable(required = true )int id){
        log.info("è stato richiesto il comando findGaraWithTabella");
        return partecipazioneGaraService.findTabellaForGara(id);
    }

    @GetMapping("/partecipazioni")
    @ResponseBody
    public List<PartecipazioneGaraDTO> getAllPartecipazioni(){
        log.info("è stato richiesto il comando getAllPartecipazioni");
        return partecipazioneGaraService.getAllPartecipazioni();
    }

    @GetMapping("/partecipazioni/{id}")
    @ResponseBody
    public PartecipazioneGaraDTO getPartecipazioneByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando getPartecipazioneByID con id {}",id);
        return partecipazioneGaraService.getPartecipazioneByID(id);
    }

    @PostMapping("/partecipazioni")
    @ResponseBody
    public PartecipazioneGaraDTO postPartecipazione (@RequestBody @Valid PartecipazioneGaraDTO partecipazioneGaraDTO){
        log.info("è stato richiesto il comando postPartecipazione ");
        return partecipazioneGaraService.postPartecipazioni(partecipazioneGaraDTO);
    }

    @PutMapping("/partecipazioni/{id}")
    @ResponseBody
    public boolean modificaPartecipazioni (@PathVariable int id, @RequestBody @Valid PartecipazioneGaraDTO partecipazioneGaraDTO){
        log.info("è stato richiesto il comando modificaPartecipazioni");
        Boolean aBoolean= partecipazioneGaraService.modificaPartecipazioni(id,partecipazioneGaraDTO);
        log.info("il comando modificaPartecipazioni è : {}", aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/partecipazioni/{id}")
    @ResponseBody
    public boolean deletePartecipazioni(@PathVariable int id){
        log.info("è stato richiesto il comando deletePartecipazioni");
        Boolean aBoolean=partecipazioneGaraService.deletePartecipazioni(id);
        log.info("il comando deleteGara è : {}",aBoolean);
        return aBoolean;
    }


}
