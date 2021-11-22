package com.triana.realestatev2.repository;


import com.triana.realestatev2.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViviendaRepository
        extends JpaRepository<Vivienda,Long> {
}
