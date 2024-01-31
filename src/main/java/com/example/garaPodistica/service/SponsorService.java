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

    // Dipendenza per l'accesso al repository degli sponsor
    @Autowired
    SponsorRepo sponsorRepo;

    // Metodo per ottenere tutti gli sponsor e trasformarli in DTO
    public List<SponsorDTO> getAllSponsor() {
        List<SponsorDTO> sponsorDTOList = new ArrayList<>();
        for(Sponsor sponsor : sponsorRepo.findAll()) {
            sponsorDTOList.add(trasformazioneSponsorInSponsorDTO(sponsor));
        }
        return sponsorDTOList;
    }

    // Metodo per ottenere uno sponsor tramite ID e trasformarlo in DTO
    public SponsorDTO getSponsorByID(int id) {
        Optional<Sponsor> sponsorOptional = sponsorRepo.findById(id);
        if(!sponsorOptional.isPresent()) {
            log.info("non è stato trovato lo sponsor con questo id");
            return null;
        }
        Sponsor sponsor = sponsorOptional.get();
        return trasformazioneSponsorInSponsorDTO(sponsor);
    }

    // Metodo per creare un nuovo sponsor
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

    // Metodo per modificare uno sponsor esistente
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

    // Metodo per eliminare uno sponsor tramite ID
    public boolean deleteSponsor(int id) {
        Optional<Sponsor> sponsorOptional = sponsorRepo.findById(id);
        if(sponsorOptional.isPresent()) {
            sponsorRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Metodo per trasformare un oggetto Sponsor in un DTO
    public SponsorDTO trasformazioneSponsorInSponsorDTO(Sponsor sponsor) {
        SponsorDTO sponsorDTO = new SponsorDTO(sponsor.getId(), sponsor.getNome(), sponsor.getTipologia(), sponsor.isItaliano());
        return sponsorDTO;
    }
}

