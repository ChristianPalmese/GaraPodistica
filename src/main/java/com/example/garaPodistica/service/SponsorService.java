package com.example.garaPodistica.service;

import com.example.garaPodistica.controller.dto.AtletaDTO;
import com.example.garaPodistica.controller.dto.SponsorDTO;
import com.example.garaPodistica.modello.Atleta;
import com.example.garaPodistica.modello.Sponsor;
import com.example.garaPodistica.repository.SponsorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SponsorService {

    @Autowired
    SponsorRepo sponsorRepo;

    /**
     * Metodo per ottenere tutti gli sponsor e trasformarli in DTO
     * @return List<SponsorDTO>
     */
    public List<SponsorDTO> getAllSponsor() {
        List<SponsorDTO> sponsorDTOList = new ArrayList<>();
        for(Sponsor sponsor : sponsorRepo.findAll()) {
            sponsorDTOList.add(trasformazioneSponsorInSponsorDTO(sponsor));
        }
        return sponsorDTOList;
    }

    /**
     * Metodo per ottenere uno sponsor tramite ID e trasformarlo in DTO
     * @param id : identifica lo sponsor che si sta puntando
     * @return SponsorDTO
     */
    public SponsorDTO getSponsorByID(int id) {
        Optional<Sponsor> sponsorOptional = sponsorRepo.findById(id);
        if(!sponsorOptional.isPresent()) {
            log.info("non è stato trovato lo sponsor con questo id");
            return null;
        }
        Sponsor sponsor = sponsorOptional.get();
        return trasformazioneSponsorInSponsorDTO(sponsor);
    }

    /**
     * Metodo per creare un nuovo sponsor
     * @param sponsorDTO : contiene le informazioni per l'inserimento del nuovo sponsor
     * @return SponsorDTO
     */
    public SponsorDTO postSponsor(SponsorDTO sponsorDTO) {
        Boolean aBoolean = sponsorRepo.existsById(sponsorDTO.getId());
        if (aBoolean == true) {
            log.info("è stato trovato già lo sponsor con questo id");
            return null;
        }
        Sponsor sponsor = new Sponsor(sponsorDTO.getNome(), sponsorDTO.getTipologia(), sponsorDTO.isItaliano());
        sponsor = sponsorRepo.save(sponsor);
        return trasformazioneSponsorInSponsorDTO(sponsor);
    }

    /**
     * Metodo per modificare uno sponsor esistente
     * @param id : dello sponsor che si sta puntando per la modifica
     * @param sponsorDTO : SponsorDTO che contiene le informazioni per la modifica
     * @return boolean : viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     */
    public boolean modificaSponsor(int id, SponsorDTO sponsorDTO) {
        Optional<Sponsor> sponsorOptional = sponsorRepo.findById(id);
        if (!sponsorOptional.isPresent()) {
            log.info("non è stato trovato lo sponsor da modificare con id {}", id);
            return false;
        }
        Sponsor sponsor = sponsorOptional.get();
        sponsor.setNome(sponsorDTO.getNome());
        sponsor.setTipologia(sponsorDTO.getTipologia());
        sponsor.setItaliano(sponsorDTO.isItaliano());
        sponsorRepo.save(sponsor);
        return true;
    }

    /**
     * Metodo per eliminare uno sponsor tramite ID
     * @param id : dello sponsor che si sta cercando
     * @return boolean : viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     */
    public boolean deleteSponsor(int id) {
        Optional<Sponsor> sponsorOptional = sponsorRepo.findById(id);
        if(sponsorOptional.isPresent()) {
            sponsorRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo per trasformare un oggetto Sponsor in un DTO
     * @param sponsor : lo sponsor da trasformare
     * @return lo sponsor trasformato in : SponsorDTO
     */
    public SponsorDTO trasformazioneSponsorInSponsorDTO(Sponsor sponsor) {
        SponsorDTO sponsorDTO = new SponsorDTO(sponsor.getId(), sponsor.getNome(), sponsor.getTipologia(), sponsor.isItaliano());
        return sponsorDTO;
    }
}

