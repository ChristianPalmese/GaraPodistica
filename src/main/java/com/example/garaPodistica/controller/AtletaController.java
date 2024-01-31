package com.example.garaPodistica.controller;

import com.example.garaPodistica.controller.dto.AtletaDTO;
import com.example.garaPodistica.controller.dto.RigaTabellaAtletiDTO;
import com.example.garaPodistica.exeptions.AtletaNotFoundExeption;
import com.example.garaPodistica.modello.Atleta;
import com.example.garaPodistica.service.AtletaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class AtletaController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */
    @Autowired
    AtletaService atletaService;

    @GetMapping("/atleta")
    @ResponseBody
    public List<AtletaDTO> getAllAtleta(){
        log.info("è stato richiesto il comando getALLAtleta");
        return atletaService.getAllAtleta();
    }

    @GetMapping("/atleta/{id}")
    @ResponseBody
    public AtletaDTO findAtletaByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando findAtletaByID");
        return atletaService.findAtletaByID(id);
    }
    @PostMapping("/atleta")
    @ResponseBody
    public AtletaDTO postAtleta(@RequestBody @Valid AtletaDTO atletaDTO){
        log.info("è stato richiesto il comando postAtleta");
        return atletaService.postAtleta(atletaDTO);
    }

    @PutMapping("/atleta/{id}")
    @ResponseBody
    public boolean modificaAtleta(@PathVariable int id, @RequestBody @Valid AtletaDTO atletaDTO){
        log.info("è stata richiesta la modifica dell'atleta con id {} ",id);
        Boolean aBoolean=atletaService.modificaAtleta(id,atletaDTO);
        log.info("la modifica è avvenuta : {} " , aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/atleta/{id}")
    @ResponseBody
    public boolean deletAtleta(@PathVariable int id){
        log.info("è stata richiesta la cancellasione dell'atleta con id {} ", id);
        Boolean aBoolean=atletaService.deleteAtleta(id);
        log.info("la cancellazione è avvenuta : {} ", aBoolean);
        return aBoolean;

    }
    @ExceptionHandler({AtletaNotFoundExeption.class})
    public ResponseEntity handleExeption(Exception x ){
        String xMessage=x.getMessage();
        return new ResponseEntity(xMessage, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/atletaPercentuali")
    @ResponseBody
    public List<RigaTabellaAtletiDTO> getAllPercentuali(){
        log.info("è stato richiesto il comando getALLPercentuali");
        return atletaService.findStatistica();
    }

}
