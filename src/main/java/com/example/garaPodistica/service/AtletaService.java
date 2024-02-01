package com.example.garaPodistica.service;

import com.example.garaPodistica.controller.dto.AtletaDTO;
import com.example.garaPodistica.controller.dto.RigaTabellaAtletiDTO;
import com.example.garaPodistica.exeptions.AtletaNotFoundExeption;
import com.example.garaPodistica.modello.Atleta;
import com.example.garaPodistica.repository.AtletaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AtletaService {
    @Autowired
    AtletaRepo atletaRepo;

    /**
     * Metodo per ottenere tutti gli atleti
     * @return List<AtletaDTO>
     */
    public List<AtletaDTO> getAllAtleta() {
        // Inizializza una lista vuota per gli AtletaDTO
        List<AtletaDTO> atletaDTOList = new ArrayList<>();

        // Recupera tutti gli atleti dal repository
        List<Atleta> atletaList = atletaRepo.findAll();

        // Trasforma ciascun Atleta in AtletaDTO e aggiungilo alla lista risultante
        for (Atleta atleta : atletaList) {
            atletaDTOList.add(trasformazioneAtletaDTOInAtleta(atleta));
        }

        // Restituisci la lista degli AtletaDTO
        return atletaDTOList;
    }

    /**
     * Metodo per trovare un atleta per ID
     * @param id : identifica l'atleta che si sta puntando
     * @return AtletaDTO
     */
    public AtletaDTO findAtletaByID(int id) {
        // Cerca un atleta nel repository tramite ID
        Optional<Atleta> atletaOptional = atletaRepo.findById(id);

        // Se l'atleta non viene trovato, lancia un'eccezione
        if (!atletaOptional.isPresent()) {
            log.info("Non è stato trovato l'utente con id {}", id);
            throw new AtletaNotFoundExeption("Atleta con id " + id + " non trovato");
        }

        // Trasforma l'Atleta trovato in AtletaDTO e restituiscilo
        Atleta atleta = atletaOptional.get();
        return trasformazioneAtletaDTOInAtleta(atleta);
    }

    /**
     * Metodo per aggiungere un nuovo atleta
     * @param atletaDTO : contiene le informazioni per l'inserimento dell'atleta
     * @return AtletaDTO
     */
    public AtletaDTO postAtleta(AtletaDTO atletaDTO) {
        // Verifica se l'atleta esiste già nel repository
        Boolean aBoolean = atletaRepo.existsById(atletaDTO.getId());

        // Se l'atleta esiste già, restituisci null
        if (aBoolean == true) {
            log.info("È stato trovato già questo atleta");
            return null;
        }

        // Crea un nuovo atleta utilizzando i dati forniti e salvalo nel repository
        Atleta atleta = new Atleta(atletaDTO.getNome(), atletaDTO.getCognome(), atletaDTO.getCitta());
        atleta = atletaRepo.save(atleta);

        // Trasforma il nuovo Atleta in AtletaDTO e restituiscilo
        return trasformazioneAtletaDTOInAtleta(atleta);
    }

    /**
     * Metodo per modificare un atleta esistente
     * @param id : identifica l'atleta che si sta puntando
     * @param atletaDTO : contiene le informazioni per la modifica dell'atleta
     * @return boolean viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     */
    public boolean modificaAtleta(int id, AtletaDTO atletaDTO) {
        // Cerca l'atleta nel repository tramite ID
        Optional<Atleta> atletaOptional = atletaRepo.findById(id);

        // Se l'atleta non viene trovato, restituisci false
        if (!atletaOptional.isPresent()) {
            log.info("Non è stato trovato l'atleta con questo id {}", id);
            return false;
        }

        // Modifica l'atleta con i nuovi dati forniti
        Atleta atleta = atletaOptional.get();
        atleta.setNome(atletaDTO.getNome());
        atleta.setCognome(atletaDTO.getCognome());
        atleta.setCitta(atletaDTO.getCitta());
        atletaRepo.save(atleta);

        // Restituisci true per indicare che la modifica è avvenuta con successo
        return true;
    }

    /** Metodo per eliminare un atleta per ID
     *
     * @param id l'id dell'utente da cancellare
     * @return un booleano che vale true se la cancellazione è avvenuta, false nel caso contrario
     */
    public boolean deleteAtleta(int id) {
        // Cerca l'atleta nel repository tramite ID
        Optional<Atleta> atletaOptional = atletaRepo.findById(id);

        // Se l'atleta non viene trovato, restituisci false
        if (!atletaOptional.isPresent()) {
            return false;
        }

        // Se l'atleta viene trovato, elimina l'atleta dal repository
        atletaRepo.deleteById(id);
        return true;
    }

    /**
     * Metodo per calcolare le statistiche sulla frequenza delle città degli atleti
     * @return List<RigaTabellaAtletiDTO>
     */
    public List<RigaTabellaAtletiDTO> findStatistica() {
        //Recupera tutti gli atleti dal repository
        List<Atleta> listaAtleti = atletaRepo.findAll();

        // Inizializza una mappa per tenere traccia del conteggio degli atleti per città
        Map<String, Integer> conteggioAtletiPerCitta = new HashMap<String, Integer>();

        // Conteggio totale degli atleti
        int totaleAtleti = listaAtleti.size();

        // Calcola il conteggio degli atleti per città
        for (Atleta atleta : listaAtleti) {
            String citta = atleta.getCitta();

            if (conteggioAtletiPerCitta.containsKey(citta)) {
                conteggioAtletiPerCitta.put(citta, conteggioAtletiPerCitta.get(citta) + 1);
            } else {
                conteggioAtletiPerCitta.put(citta, 1);
            }
        }

        // Calcola le percentuali per ogni città e crea gli oggetti DTO
        List<RigaTabellaAtletiDTO> listaFrequenzeCittaDTO = new ArrayList<>();
        for (String citta : conteggioAtletiPerCitta.keySet()) {
            int conteggio = conteggioAtletiPerCitta.get(citta);
            double percentuale = (double) conteggio / totaleAtleti * 100.0;
            RigaTabellaAtletiDTO dto = new RigaTabellaAtletiDTO(citta, conteggio, percentuale);
            listaFrequenzeCittaDTO.add(dto);
        }

        // Restituisci la lista degli oggetti DTO contenenti le statistiche
        return listaFrequenzeCittaDTO;
    }

    /**
     * Metodo per trasformare l'alteta in AtletaDTO per il ritorno nel controller
     * @param atleta : atleta da trasformare
     * @return atleta trasformato in : AtletaDTO
     */
    public AtletaDTO trasformazioneAtletaDTOInAtleta(Atleta atleta){
        AtletaDTO atletaDTO= new AtletaDTO(atleta.getId(),atleta.getNome(),atleta.getCognome(),atleta.getCitta());
        return atletaDTO;
    }
}
