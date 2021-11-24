package com.triana.realestatev2.dto.ViviendaDto;

import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDto v){

        return Vivienda.builder()
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .build();
    }

    public GetViviendaDto viviendaToGetViviendaDto(Vivienda v){

        return GetViviendaDto.builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .build();

    }

    public GetViviendaPropietarioDto viviendaToGetViviendaPropietarioDto(Vivienda v, Usuario u){

        return GetViviendaPropietarioDto.builder().
                descripcion(v.getDescripcion())
                .titulo(v.getTitulo())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .nombrePropietario(u.getNombre())
                .apellidosPropietario(u.getApellidos())
                .emailPropietario(u.getEmail())
                .telefonoPropietario(u.getTelefono())
                .build();

    }

}
