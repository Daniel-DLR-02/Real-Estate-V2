package com.triana.realestatev2.service;


import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.repository.InmobiliariaRepository;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class InmobiliariaService
        extends BaseService<Inmobiliaria,Long, InmobiliariaRepository> {

    public boolean comprobarGestorPerteneceInmobiliaria(Usuario gest, Inmobiliaria inmo){

        boolean pertenece=false;

        for(Usuario gestor : inmo.getGestores()){
            if(gestor.getId().equals(gest.getId()))
                pertenece=true;
        }

        return pertenece;
    }

}