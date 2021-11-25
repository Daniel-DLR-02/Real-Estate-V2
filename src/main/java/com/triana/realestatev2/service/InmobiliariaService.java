package com.triana.realestatev2.service;


import com.triana.realestatev2.dto.InmobiliariaDto.GetInmobiliariaDto;
import com.triana.realestatev2.dto.InmobiliariaDto.InmobiliariaDtoConverter;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.repository.InmobiliariaRepository;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InmobiliariaService
        extends BaseService<Inmobiliaria,Long, InmobiliariaRepository> {

    private final InmobiliariaDtoConverter dtoConverter;


    public boolean comprobarGestorPerteneceInmobiliaria(Usuario gest, Inmobiliaria inmo){

        boolean pertenece=false;

        for(Usuario gestor : inmo.getGestores()){
            if(gestor.getId().equals(gest.getId()))
                pertenece=true;
        }

        return pertenece;
    }

    public List<GetInmobiliariaDto> listaInmoToListaGetInmoDto(List<Inmobiliaria> inmo){

        List<GetInmobiliariaDto> listaGetInmoDto = new ArrayList<>();

        inmo.stream().forEach(i -> {
            listaGetInmoDto.add(dtoConverter.inmobiliariaToGetInmobiliariaDto(i));
        });

        return listaGetInmoDto;
    }

}