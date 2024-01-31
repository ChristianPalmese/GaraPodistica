package com.example.garaPodistica.repository;

import com.example.garaPodistica.modello.Gara;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GaraRepo extends JpaRepository<Gara,Integer> {

    public Optional<Gara> findGaraByCodice(String codice);


}
