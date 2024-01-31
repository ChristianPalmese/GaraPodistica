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
    /*
    Questa annotazione @Autowired viene utilizzata per l'iniezione di dipendenze.
    Qui viene iniettato un bean di tipo GaraRepo all'interno di questa classe GaraService,
    consentendo l'utilizzo dei metodi definiti in GaraRepo.
     */
    @Autowired
    GaraRepo garaRepo;

    //Questo metodo recupera tutte le gare dal repository garaRepo e le trasforma in oggetti GaraDTO, restituendo infine una lista di GaraDTO.
    public List<GaraDTO> getAllGara(){
        List<GaraDTO> garaDTOList=new ArrayList<>();
       for(Gara gara:garaRepo.findAll()){
           garaDTOList.add(trasformazioneGaraInGaraDTO(gara));
       }
       return garaDTOList;
    }

    //Questo metodo recupera una gara dal repository garaRepo basata sull'ID fornito e la trasforma in un oggetto GaraDTO.
    public GaraDTO getGaraByID(int id){
        Optional<Gara> garaOptional =garaRepo.findById(id);
        if(!garaOptional.isPresent()){
            log.info("non è presente nessuna gara con questo id {}",id);
            return null;
        }
        Gara gara=garaOptional.get();
        return trasformazioneGaraInGaraDTO(gara);
    }

    //Questo metodo crea una nuova gara utilizzando i dati forniti tramite garaDTO, la salva nel repository e restituisce l'oggetto GaraDTO corrispondente.
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

    //Questo metodo modifica una gara esistente identificata dall'ID fornito con i nuovi dati forniti tramite garaDTO.

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

    //Questo metodo elimina una gara dal repository basata sull'ID fornito.
    public boolean deleteGara(int id){
        Optional<Gara> garaOptional=garaRepo.findById(id);
        if(garaOptional.isPresent()){
            garaRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //Questo metodo restituisce una lista di gare il cui chilometraggio è uguale o inferiore al valore fornito.
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

    //Questo metodo trasforma un oggetto Gara in un oggetto GaraDTO.

    public GaraDTO trasformazioneGaraInGaraDTO(Gara gara){
        GaraDTO garaDTO= new GaraDTO(gara.getId(),gara.getCodice(),gara.getDescrizione(),gara.getLunghezzaKm(),gara.getDataSvolgimento());
        return garaDTO;

    }
}
