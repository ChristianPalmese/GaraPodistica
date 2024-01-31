package com.example.garaPodistica.service;

import com.example.garaPodistica.controller.dto.GaraDTO;
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
    @Autowired
    PartecipazioneGaraRepo partecipazioneGaraRepo;

    @Autowired
    GaraRepo garaRepo;
    @Autowired
    AtletaRepo atletaRepo;

    /**
     * ricerca delle informazioni per la tabella della gara con id scelto dall'utente
     * @param id
     * @return
     */
    public List<RigaTabellaPartecipazioneDTO> findTabellaForGara(int id){
        return partecipazioneGaraRepo.findAllByGara_Id(id).stream()
                .map(p -> new RigaTabellaPartecipazioneDTO(p.getAtleta().getNome(),p.getOrarioArrivo()))
                .collect(Collectors.toList());
    }

    public List<PartecipazioneGaraDTO> getAllPartecipazioni(){
        List<PartecipazioneGaraDTO> partecipazioneGaraDTOList= new ArrayList<>();
        for(PartecipazioneGara partecipazioneGara:partecipazioneGaraRepo.findAll()){
            partecipazioneGaraDTOList.add(trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara));
        }
        return partecipazioneGaraDTOList;
    }

    public PartecipazioneGaraDTO getPartecipazioneByID(int id){
        Optional<PartecipazioneGara> partecipazioneGaraOptional =partecipazioneGaraRepo.findById(id);
        if(!partecipazioneGaraOptional.isPresent()){
            log.info("non è presente nessuna partecipazione con questo id {}",id);
            return null;
        }
        PartecipazioneGara partecipazioneGara= partecipazioneGaraOptional.get();
        return trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara);
    }

    public PartecipazioneGaraDTO postPartecipazioni(PartecipazioneGaraDTO partecipazioneGaraDTO){
        Boolean verifica = partecipazioneGaraRepo.existsById(partecipazioneGaraDTO.getId());
        if(verifica==true){
            log.info("la partecipazione gia è esistente con questo id");
            throw new GaraCodiceExeption("la gara con questo id "+partecipazioneGaraDTO.getId()+" non è stata trovata");
        }
        Atleta atleta=atletaRepo.findById(partecipazioneGaraDTO.getAtletaID()).orElseThrow(AtletaNotFoundExeption::new);
        Gara gara= garaRepo.findById(partecipazioneGaraDTO.getGaraID()).orElseThrow(GaraNotFoundExeption::new);
        PartecipazioneGara partecipazioneGara = new PartecipazioneGara(atleta,gara,partecipazioneGaraDTO.getOrarioArrivo());
        partecipazioneGara=partecipazioneGaraRepo.save(partecipazioneGara);
        return trasformazionePartecipazioneGarainPartecipazioneGaraDTO(partecipazioneGara);
    }


    public boolean modificaPartecipazioni(int id, PartecipazioneGaraDTO partecipazioneGaraDTO){
        PartecipazioneGara partecipazioneGara =partecipazioneGaraRepo.findById(id).orElseThrow(PartecipazioneGaraNotFound::new);
        Atleta atleta=atletaRepo.findById(partecipazioneGaraDTO.getAtletaID()).orElseThrow(AtletaNotFoundExeption::new);
        Gara gara= garaRepo.findById(partecipazioneGaraDTO.getGaraID()).orElseThrow(GaraNotFoundExeption::new);
        partecipazioneGara.setAtleta(atleta);
        partecipazioneGara.setGara(gara);
        partecipazioneGara.setOrarioArrivo(partecipazioneGaraDTO.getOrarioArrivo());
        partecipazioneGaraRepo.save(partecipazioneGara);
        return true;
    }

    public boolean deletePartecipazioni(int id){
        PartecipazioneGara partecipazioneGara= partecipazioneGaraRepo.findById(id).orElseThrow(PartecipazioneGaraNotFound::new);
        partecipazioneGaraRepo.deleteById(id);
        return true;
    }










    public PartecipazioneGaraDTO trasformazionePartecipazioneGarainPartecipazioneGaraDTO(PartecipazioneGara partecipazioneGara){
        PartecipazioneGaraDTO partecipazioneGaraDTO= new PartecipazioneGaraDTO(partecipazioneGara.getId(),partecipazioneGara.getAtleta().getId(),partecipazioneGara.getGara().getId(),partecipazioneGara.getOrarioArrivo());
        return partecipazioneGaraDTO;
    }

}
