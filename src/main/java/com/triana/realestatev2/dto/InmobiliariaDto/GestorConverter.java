package com.triana.realestatev2.dto.InmobiliariaDto;

import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import org.springframework.stereotype.Component;

@Component
public class GestorConverter {

    public Usuario createGestorEnInmoDtoToGestor(CreateGestorEnInmoDto gest, Inmobiliaria inmo){
        return Usuario.builder()
                .nombre(gest.getNombre())
                .apellidos(gest.getApellidos())
                .direccion(gest.getDireccion())
                .email(gest.getEmail())
                .telefono(gest.getTelefono())
                .avatar(gest.getAvatar())
                .password(gest.getPassword())
                .role(UsuarioRole.GESTOR)
                .inmobiliaria(inmo)
                .build();
    }
}
