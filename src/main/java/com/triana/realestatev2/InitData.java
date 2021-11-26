package com.triana.realestatev2;

import com.triana.realestatev2.dto.InmobiliariaDto.CreateInmobiliariaDto;
import com.triana.realestatev2.dto.InteresaDto.CreateInteresaDto;
import com.triana.realestatev2.dto.InteresaDto.InteresaDtoConverter;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.InmobiliariaService;
import com.triana.realestatev2.service.InteresaService;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.dto.CreateUsuarioDto;
import com.triana.realestatev2.users.dto.CreateUsuarioGestorDto;
import com.triana.realestatev2.users.dto.UsuarioDtoConverter;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Component
public class InitData {

    private final UsuarioService usuarioService;
    private final InmobiliariaService inmobiliariaService;
    private final ViviendaService viviendaService;
    private final UsuarioDtoConverter usuarioDtoConverter;
    private final InteresaService interesaService;
    private final InteresaDtoConverter inteDtoConverter;

    @PostConstruct
    public void data(){


        //Creación de usuarios ADMIN
        CreateUsuarioDto user= CreateUsuarioDto.builder().nombre("Daniel").apellidos("de Luna").direccion("C/ Condes del Bustillo").email("deluna.rodan21@gmail.com").telefono("123456789").avatar("dsadasd.jpeg").password("qwerty").password2("qwerty").build();
        usuarioService.saveAdmin(user);


        //Creación de usuarios PROPIETARIOS
        Usuario userProp1= usuarioService.saveProp(CreateUsuarioDto.builder().nombre("Antonio").apellidos("López").telefono("12345678").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("propVivienda1@gmail.com").password("asdfg").password2("asdfg").build());
        Usuario userProp2= usuarioService.saveProp(CreateUsuarioDto.builder().nombre("Jose").apellidos("Solís").telefono("435267434").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("propVivienda2@gmail.com").password("asdfg").password2("asdfg").build());
        Usuario userProp3= usuarioService.saveProp(CreateUsuarioDto.builder().nombre("Carlos").apellidos("Albandea").telefono("357432674").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("propVivienda3@gmail.com").password("asdfg").password2("asdfg").build());
        Usuario userProp4= usuarioService.saveProp(CreateUsuarioDto.builder().nombre("Saúl").apellidos("Peira").telefono("305849293").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("propVivienda4@gmail.com").password("asdfg").password2("asdfg").build());
        Usuario userProp5= usuarioService.saveProp(CreateUsuarioDto.builder().nombre("Candela").apellidos("Narváez").telefono("142038250").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("propVivienda5@gmail.com").password("asdfg").password2("asdfg").build());


        //Creación Inmobiliarias

        Inmobiliaria inmo1 = inmobiliariaService.save(Inmobiliaria.builder().nombre("Idealista").email("emailinmo1@gmail.com").telefono("987654432").build());
        Inmobiliaria inmo2 = inmobiliariaService.save(Inmobiliaria.builder().nombre("Melibero").email("emailinmo2@gmail.com").telefono("987654432").build());
        Inmobiliaria inmo3 = inmobiliariaService.save(Inmobiliaria.builder().nombre("HomeSeller").email("emailinmo3@gmail.com").telefono("987654432").build());
        Inmobiliaria inmo4 = inmobiliariaService.save(Inmobiliaria.builder().nombre("Housfy").email("emailinmo4@gmail.com").telefono("987654432").build());
        Inmobiliaria inmo5 = inmobiliariaService.save(Inmobiliaria.builder().nombre("TIKO").email("emailinmo5@gmail.com").telefono("987654432").build());


        //Creación de gestores

        Usuario gestor1= usuarioService.saveGestor(CreateUsuarioGestorDto.builder().nombre("Pepe").apellidos("López").telefono("12345678").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("gest1@gmail.com").password("asdfg").password2("asdfg").idInmobiliaria(inmo1.getId()).build());
        Usuario gestor2= usuarioService.saveGestor(CreateUsuarioGestorDto.builder().nombre("Manuel").apellidos("Solís").telefono("435267434").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("gest2@gmail.com").password("asdfg").password2("asdfg").idInmobiliaria(inmo2.getId()).build());
        Usuario gestor3= usuarioService.saveGestor(CreateUsuarioGestorDto.builder().nombre("Jorge").apellidos("Albandea").telefono("357432674").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("gest3@gmail.com").password("asdfg").password2("asdfg").idInmobiliaria(inmo3.getId()).build());
        Usuario gestor4= usuarioService.saveGestor(CreateUsuarioGestorDto.builder().nombre("Ángel").apellidos("Peira").telefono("305849293").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("gest4@gmail.com").password("asdfg").password2("asdfg").idInmobiliaria(inmo4.getId()).build());
        Usuario gestor5= usuarioService.saveGestor(CreateUsuarioGestorDto.builder().nombre("Fernando").apellidos("Narváez").telefono("142038250").direccion("C/ Condes del bustillo").avatar("http://igerent.com/sites/default/files/2021-02/trademark-owner.jpg").email("gest5@gmail.com").password("asdfg").password2("asdfg").idInmobiliaria(inmo5.getId()).build());


        //Creación de viviendas
        Vivienda vivienda1 = Vivienda.builder().titulo("Casa1").descripcion("Desc1").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000.0).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50.0).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda2 = Vivienda.builder().titulo("Casa2").descripcion("Desc2").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(2000).tipo("alquiler").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda3 = Vivienda.builder().titulo("Casa3").descripcion("Desc3").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda4 = Vivienda.builder().titulo("Casa4").descripcion("Desc4").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(2000).tipo("alquiler").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda5 = Vivienda.builder().titulo("Casa5").descripcion("Desc5").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda6 = Vivienda.builder().titulo("Casa6").descripcion("Desc6").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(2000).tipo("alquiler").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda7 = Vivienda.builder().titulo("Casa7").descripcion("Desc7").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda8 = Vivienda.builder().titulo("Casa8").descripcion("Desc8").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(2000).tipo("alquiler").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda9 = Vivienda.builder().titulo("Casa9").descripcion("Desc9").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();
        Vivienda vivienda10 = Vivienda.builder().titulo("Casa10").descripcion("Desc10").avatar("https://images.emojiterra.com/google/android-10/512px/1f3e0.png").lating("43.4343,32.3234").precio(500000).tipo("venta").direccion("C/ Condes del bustillo").codigoPostal("41010").poblacion("Sevilla")
                .provincia("Sevilla").numHabitaciones(4).numBanios(2).metrosCuadrados(50).tienePiscina(false).tieneGaraje(true).tieneAscensor(true).build();

        vivienda1.addPropietario(userProp1);
        vivienda2.addPropietario(userProp1);
        vivienda3.addPropietario(userProp2);
        vivienda4.addPropietario(userProp2);
        vivienda5.addPropietario(userProp3);
        vivienda6.addPropietario(userProp3);
        vivienda7.addPropietario(userProp4);
        vivienda8.addPropietario(userProp4);
        vivienda9.addPropietario(userProp5);
        vivienda10.addPropietario(userProp5);

        vivienda1.addInmobiliaria(inmo1);
        vivienda2.addInmobiliaria(inmo1);
        vivienda3.addInmobiliaria(inmo2);
        vivienda4.addInmobiliaria(inmo2);
        vivienda5.addInmobiliaria(inmo3);
        vivienda6.addInmobiliaria(inmo3);
        vivienda7.addInmobiliaria(inmo4);
        vivienda8.addInmobiliaria(inmo4);
        vivienda9.addInmobiliaria(inmo5);
        vivienda10.addInmobiliaria(inmo5);

        viviendaService.save(vivienda1);
        viviendaService.save(vivienda2);
        viviendaService.save(vivienda3);
        viviendaService.save(vivienda4);
        viviendaService.save(vivienda5);
        viviendaService.save(vivienda6);
        viviendaService.save(vivienda7);
        viviendaService.save(vivienda8);
        viviendaService.save(vivienda9);
        viviendaService.save(vivienda10);

        //Crear Interesa

        Interesa interesa1 = interesaService.save(inteDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Me interesa la vivienda").build(),userProp1,vivienda9));
        Interesa interesa2 = interesaService.save(inteDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Me interesa la vivienda").build(),userProp2,vivienda9));
        Interesa interesa3 = interesaService.save(inteDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Me interesa la vivienda").build(),userProp3,vivienda5));
        Interesa interesa4 = interesaService.save(inteDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Me interesa la vivienda").build(),userProp2,vivienda1));
        Interesa interesa5 = interesaService.save(inteDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Me interesa la vivienda").build(),userProp5,vivienda10));




    }
}
