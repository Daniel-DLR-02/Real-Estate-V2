package com.triana.realestatev2;

import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.ViviendaService;
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
    private final ViviendaService viviendaService;

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

        CreateUsuarioDto userPropVivienda= CreateUsuarioDto.builder()
                .nombre("Propietario")
                .apellidos("posee vivienda")
                .telefono("12345678")
                .direccion("C/ Mi calle")
                .avatar("dasdasdas.jpeg")
                .email("propVivienda@gmail.com")
                .password("asdfg")
                .password2("asdfg")
                .build();



        Vivienda vivienda1 = Vivienda.builder()
                .titulo("Casa")
                .descripcion("Desc")
                .precio(500000)
                .tipo("alquiler")
                .build();

        vivienda1.addPropietario(usuarioService.saveProp(userPropVivienda));

        viviendaService.save(vivienda1);
    }
}
