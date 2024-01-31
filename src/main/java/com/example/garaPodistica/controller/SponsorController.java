package com.example.garaPodistica.controller;

import com.example.garaPodistica.controller.dto.AtletaDTO;
import com.example.garaPodistica.controller.dto.GaraDTO;
import com.example.garaPodistica.controller.dto.SponsorDTO;
import com.example.garaPodistica.service.SponsorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class SponsorController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */

    @Autowired
    SponsorService sponsorService;
    @GetMapping("/sponsor")
    @ResponseBody
    public List<SponsorDTO> getAllSponsor(){
        log.info("è stato richiesto il comando getALlSponsor");
        return sponsorService.getAllSponsor();
    }

    @GetMapping("/sponsor/{id}")
    @ResponseBody
    public GaraDTO getSponsorByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando getSponsorByID con id {}",id);
        return getSponsorByID(id);
    }

    @PostMapping("/sponsor")
    @ResponseBody
    public SponsorDTO postSponsor (@RequestBody @Valid SponsorDTO sponsorDTO){
        log.info("è stato richiesto il comando postSponsor");
        return sponsorService.postSponsor(sponsorDTO);
    }
    @PutMapping("/sponsor/{id}")
    @ResponseBody
    public boolean modificaSponsor(@PathVariable int id, @RequestBody @Valid SponsorDTO sponsorDTO){
        log.info("è stata richiesta la modifica dello sponsor con id {} ",id);
        Boolean aBoolean=sponsorService.modificaSponsor(id,sponsorDTO);
        log.info("la modifica è avvenuta : {} " , aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/sponsor/{id}")
    @ResponseBody
    public boolean deleteSponsor(@PathVariable int id){
        log.info("è stato richiesto il comando deleteSponsor");
        Boolean aBoolean=sponsorService.deleteSponsor(id);
        log.info("il comando deleteSponsor è : {}",aBoolean);
        return aBoolean;
    }

}
