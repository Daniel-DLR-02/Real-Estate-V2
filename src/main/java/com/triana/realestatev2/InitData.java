package com.triana.realestatev2;

import com.triana.realestatev2.users.dto.CreateUsuarioDto;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Component
public class InitData {

    private final UsuarioService usuarioService;

    @PostConstruct
    public void data(){
        CreateUsuarioDto user= CreateUsuarioDto.builder()
                .nombre("Daniel")
                .apellidos("de Luna")
                .direccion("C/ Condes del Bustillo")
                .email("deluna.rodan21@gmail.com")
                .telefono("123456789")
                .avatar("dsadasd.jpeg")
                .password("qwerty")
                .password2("qwerty")
                .build();

            usuarioService.saveAdmin(user);
    }
}
