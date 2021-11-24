package com.triana.realestatev2.users.dto;

import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto usuarioToGetUsuarioDto(Usuario u){
        return GetUsuarioDto.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .role(u.getRole())
                .build();

    }

    public GetUsuarioPropietarioDto usuarioToGetUsuarioPropietarioDto(Usuario u){
        return GetUsuarioPropietarioDto.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .direccion(u.getDireccion())
                .email(u.getEmail())
                .telefono(u.getTelefono())
                .avatar(u.getAvatar())
                .build();
    }

}
