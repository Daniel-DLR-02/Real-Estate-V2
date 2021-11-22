package com.triana.realestatev2.repository;

import com.triana.realestatev2.model.Inmobiliaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InmobiliariaRepository
        extends JpaRepository<Inmobiliaria, Long> {
}
