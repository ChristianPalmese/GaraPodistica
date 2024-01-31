package com.example.garaPodistica.repository;

import com.example.garaPodistica.modello.PartecipazioneGara;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartecipazioneGaraRepo extends JpaRepository<PartecipazioneGara,Integer> {
    public List<PartecipazioneGara> findAllByGara_Id(int id);
}
