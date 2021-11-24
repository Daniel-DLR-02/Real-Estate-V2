package com.triana.realestatev2.dto.InmobiliariaDto;

import com.triana.realestatev2.model.Inmobiliaria;
import org.springframework.stereotype.Component;


@Component
public class InmobiliariaDtoConverter {
    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDto c) {

        return Inmobiliaria.builder()
                .nombre(c.getNombre())
                .email(c.getEmail())
                .telefono(c.getTelefono())
                .build();

    }
    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto(Inmobiliaria i) {
        return GetInmobiliariaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .telefono(i.getTelefono())
                .email(i.getEmail())
                .build();
    }
}