package com.triana.realestatev2.repository;


import com.triana.realestatev2.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViviendaRepository
        extends JpaRepository<Vivienda,Long> {
}
