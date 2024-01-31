package com.example.garaPodistica.repository;

import com.example.garaPodistica.modello.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtletaRepo extends JpaRepository<Atleta,Integer> {
    public List<Atleta> findAllByCitta(String nomeCitta);
}
