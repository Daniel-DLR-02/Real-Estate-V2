package com.triana.realestatev2.users.dto;

import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto usuarioToGetUsuarioDto(Usuario u){
        return GetUsuarioDto.builder()
                .nombre(u.getNombre())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .role(u.getRole())
                .build();

    }
}
