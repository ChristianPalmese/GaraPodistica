package com.example.garaPodistica.controller;

import com.example.garaPodistica.controller.dto.GaraDTO;
import com.example.garaPodistica.exeptions.AtletaNotFoundExeption;
import com.example.garaPodistica.service.GaraService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class GaraController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */
    @Autowired
    GaraService garaService;

    @GetMapping("/gara")
    @ResponseBody
    public List<GaraDTO> getAllGara(){
        log.info("è stato richiesto il comando getALlGara");
        return garaService.getAllGara();
    }

    @GetMapping("/gara/{id}")
    @ResponseBody
    public GaraDTO getGaraByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando getGaraByID con id {}",id);
        return garaService.getGaraByID(id);
    }

    @PostMapping("/gara")
    @ResponseBody
    public GaraDTO postGara (@RequestBody @Valid GaraDTO garaDTO){
        log.info("è stato richiesto il comando postGara");
        return garaService.postGara(garaDTO);
    }

    @PutMapping("/gara/{id}")
    @ResponseBody
    public boolean modificaGara (@PathVariable int id, @RequestBody @Valid GaraDTO garaDTO){
        log.info("è stato richiesto il comando modificaGara");
        Boolean aBoolean= garaService.modificaGara(id,garaDTO);
        log.info("il comando modificaGara è : {}", aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/gara/{id}")
    @ResponseBody
    public boolean deleteGara(@PathVariable int id){
        log.info("è stato richiesto il comando deleteGara");
        Boolean aBoolean=garaService.deleteGara(id);
        log.info("il comando deleteGara è : {}",aBoolean);
        return aBoolean;
    }

    @GetMapping("/gara/{chilometraggio}")
    @ResponseBody
    public List<GaraDTO> getGaraByChilometraggio(@PathVariable(required = true) int chilometraggio){
        log.info("è stato richiesto il comando getGaraByChilometraggio con chilometraggio {}",chilometraggio);
        return garaService.getGaraByChilometraggio(chilometraggio);
    }

/*
    @GetMapping("/gara/codice")
    @ResponseBody
    public GaraDTO findGaraWithTabella(@RequestParam(value = "codice")String codice){
        log.info("è stato richiesto il comando findGaraWithTabella");

    }


 */



}
