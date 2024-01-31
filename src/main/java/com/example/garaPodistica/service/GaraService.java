package com.example.garaPodistica.service;

import com.example.garaPodistica.controller.dto.GaraDTO;
import com.example.garaPodistica.exeptions.GaraCodiceExeption;
import com.example.garaPodistica.modello.Gara;
import com.example.garaPodistica.repository.GaraRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GaraService {
    @Autowired
    GaraRepo garaRepo;

    public List<GaraDTO> getAllGara(){
        List<GaraDTO> garaDTOList=new ArrayList<>();
       for(Gara gara:garaRepo.findAll()){
           garaDTOList.add(trasformazioneGaraInGaraDTO(gara));
       }
       return garaDTOList;
    }

    public GaraDTO getGaraByID(int id){
        Optional<Gara> garaOptional =garaRepo.findById(id);
        if(!garaOptional.isPresent()){
            log.info("non è presente nessuna gara con questo id {}",id);
            return null;
        }
        Gara gara=garaOptional.get();
        return trasformazioneGaraInGaraDTO(gara);
    }

    public GaraDTO postGara(GaraDTO garaDTO){
        Boolean verifica = garaRepo.existsById(garaDTO.getId());
        if(verifica==true){
            log.info("la gara gia è esistente con questo id");
            throw new GaraCodiceExeption("la gara con questo id "+garaDTO.getId()+" non è stata trovata");
        }
        Gara gara = new Gara(garaDTO.getCodice(),garaDTO.getDescrizione(),garaDTO.getLunghezzaKm(),garaDTO.getDataSvolgimento());
        gara=garaRepo.save(gara);
        return trasformazioneGaraInGaraDTO(gara);
    }


    public boolean modificaGara(int id, GaraDTO garaDTO){
        Optional<Gara> garaOptional=garaRepo.findById(id);
        if (!garaOptional.isPresent()){
            log.info("la gara con questo id non è presente");
            return false;
        }
        Gara gara= garaOptional.get();
        gara.setCodice(gara.getCodice());
        gara.setDescrizione(garaDTO.getDescrizione());
        gara.setLunghezzaKm(garaDTO.getLunghezzaKm());
        gara.setDataSvolgimento(garaDTO.getDataSvolgimento());
        garaRepo.save(gara);
        return true;
    }

    public boolean deleteGara(int id){
        Optional<Gara> garaOptional=garaRepo.findById(id);
        if(garaOptional.isPresent()){
            garaRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<GaraDTO> getGaraByChilometraggio(int chilometraggio){
        List<Gara>garaList=garaRepo.findAll();
        for(Gara gara:garaList){
            if(gara.getLunghezzaKm()==chilometraggio||gara.getLunghezzaKm()<chilometraggio){
                garaList.add(gara);
            }
        }
        List<GaraDTO> garaDTOList= new ArrayList<>();
        for(Gara gara:garaList){
            garaDTOList.add(trasformazioneGaraInGaraDTO(gara));
        }



        return garaDTOList;
    }

/*
    public GaraDTO findGaraWithTabella(String codice){
         Optional<Gara> garaOptional=garaRepo.findGaraByCodice(codice);
         if(!garaOptional.isPresent()){
             log.info("la gara con questo codice {} non esiste",codice);
             return null;
         }

         GaraDTO garaDTO= trasformazioneGaraInGaraDTO(garaOptional.get());
         List<PartecipazioneGara> partecipazioneGaraList=partecipazioneGaraRepo.findAllByGara_Id(garaOptional.get().getId());
         for(PartecipazioneGara partecipazioneGara:partecipazioneGaraList){
             garaDTO.getPartecipazioneDTOList().add(trasformazionePreparazioneinPreparazioneDTO(partecipazioneGara));
         }
         return garaDTO;
    }

 */









    public GaraDTO trasformazioneGaraInGaraDTO(Gara gara){
        GaraDTO garaDTO= new GaraDTO(gara.getId(),gara.getCodice(),gara.getDescrizione(),gara.getLunghezzaKm(),gara.getDataSvolgimento());
        return garaDTO;

    }
}
