package com.example.garaPodistica.service;

import com.example.garaPodistica.controller.dto.PartecipazioneGaraDTO;
import com.example.garaPodistica.controller.dto.RigaTabellaPartecipazioneDTO;
import com.example.garaPodistica.exeptions.AtletaNotFoundExeption;
import com.example.garaPodistica.exeptions.GaraCodiceExeption;
import com.example.garaPodistica.exeptions.GaraNotFoundExeption;
import com.example.garaPodistica.exeptions.PartecipazioneGaraNotFound;
import com.example.garaPodistica.modello.Atleta;
import com.example.garaPodistica.modello.Gara;
import com.example.garaPodistica.modello.PartecipazioneGara;
import com.example.garaPodistica.repository.AtletaRepo;
import com.example.garaPodistica.repository.GaraRepo;
import com.example.garaPodistica.repository.PartecipazioneGaraRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartecipazioneGaraService {

    // Dipendenze per l'accesso ai repository delle partecipazioni alle gare, delle gare e degli atleti
    @Autowired
    PartecipazioneGaraRepo partecipazioneGaraRepo;
    @Autowired
    GaraRepo garaRepo;
    @Autowired
    AtletaRepo atletaRepo;

    /**
     * Metodo per trovare le informazioni da visualizzare nella tabella delle partecipazioni per una data gara.
     * Viene fornito l'ID della gara.
     * @param id L'ID della gara per la quale si desiderano le informazioni sulla partecipazione.
     * @return Una lista di righe della tabella delle partecipazioni per la gara specificata.
     */
    public List<RigaTabellaPartecipazioneDTO> findTabellaForGara(int id) {
        return partecipazioneGaraRepo.findAllByGara_Id(id).stream()
                .map(p -> new RigaTabellaPartecipazioneDTO(p.getAtleta().getNome(), p.getOrarioArrivo()))
                .collect(Collectors.toList());
    }

    // Metodo per ottenere tutte le partecipazioni e trasformarle in DTO
    public List<PartecipazioneGaraDTO> getAllPartecipazioni() {
        List<PartecipazioneGaraDTO> partecipazioneGaraDTOList = new ArrayList<>();
        for(PartecipazioneGara partecipazioneGara : partecipazioneGaraRepo.findAll()) {
            partecipazioneGaraDTOList.add(trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara));
        }
        return partecipazioneGaraDTOList;
    }

    // Metodo per ottenere una partecipazione tramite ID e trasformarla in DTO
    public PartecipazioneGaraDTO getPartecipazioneByID(int id) {
        Optional<PartecipazioneGara> partecipazioneGaraOptional = partecipazioneGaraRepo.findById(id);
        if(!partecipazioneGaraOptional.isPresent()) {
            log.info("non è presente nessuna partecipazione con questo id {}", id);
            return null;
        }
        PartecipazioneGara partecipazioneGara = partecipazioneGaraOptional.get();
        return trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara);
    }

    // Metodo per creare una nuova partecipazione
    public PartecipazioneGaraDTO postPartecipazioni(PartecipazioneGaraDTO partecipazioneGaraDTO) {
        Boolean verifica = partecipazioneGaraRepo.existsById(partecipazioneGaraDTO.getId());
        if(verifica == true) {
            log.info("la partecipazione già esiste con questo id");
            throw new GaraCodiceExeption("la gara con questo id " + partecipazioneGaraDTO.getId() + " non è stata trovata");
        }
        Atleta atleta = atletaRepo.findById(partecipazioneGaraDTO.getAtletaID()).orElseThrow(AtletaNotFoundExeption::new);
        Gara gara = garaRepo.findById(partecipazioneGaraDTO.getGaraID()).orElseThrow(GaraNotFoundExeption::new);
        PartecipazioneGara partecipazioneGara = new PartecipazioneGara(atleta, gara, partecipazioneGaraDTO.getOrarioArrivo());
        partecipazioneGara = partecipazioneGaraRepo.save(partecipazioneGara);
        return trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara);
    }

    // Metodo per modificare una partecipazione esistente
    public boolean modificaPartecipazioni(int id, PartecipazioneGaraDTO partecipazioneGaraDTO) {
        PartecipazioneGara partecipazioneGara = partecipazioneGaraRepo.findById(id).orElseThrow(PartecipazioneGaraNotFound::new);
        Atleta atleta = atletaRepo.findById(partecipazioneGaraDTO.getAtletaID()).orElseThrow(AtletaNotFoundExeption::new);
        Gara gara = garaRepo.findById(partecipazioneGaraDTO.getGaraID()).orElseThrow(GaraNotFoundExeption::new);
        partecipazioneGara.setAtleta(atleta);
        partecipazioneGara.setGara(gara);
        partecipazioneGara.setOrarioArrivo(partecipazioneGaraDTO.getOrarioArrivo());
        partecipazioneGaraRepo.save(partecipazioneGara);
        return true;
    }

    // Metodo per eliminare una partecipazione tramite ID
    public boolean deletePartecipazioni(int id) {
        PartecipazioneGara partecipazioneGara = partecipazioneGaraRepo.findById(id).orElseThrow(PartecipazioneGaraNotFound::new);
        partecipazioneGaraRepo.deleteById(id);
        return true;
    }

    // Metodo per trasformare un oggetto PartecipazioneGara in un DTO
    public PartecipazioneGaraDTO trasformazionePartecipazioneGarainPartecipazioneGaraDTO(PartecipazioneGara partecipazioneGara) {
        PartecipazioneGaraDTO partecipazioneGaraDTO = new PartecipazioneGaraDTO(partecipazioneGara.getId(), partecipazioneGara.getAtleta().getId(), partecipazioneGara.getGara().getId(), partecipazioneGara.getOrarioArrivo());
        return partecipazioneGaraDTO;
    }
}

