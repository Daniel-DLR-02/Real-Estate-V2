package com.triana.realestatev2.dto.ViviendaDto;

import com.triana.realestatev2.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDto v){
        return new Vivienda(
                v.getTitulo(),
                v.getDescripcion(),
                v.getAvatar(),
                v.getLating(),
                v.getDireccion(),
                v.getCodigoPostal(),
                v.getPoblacion(),
                v.getProvincia(),
                v.getTipo(),
                v.getPrecio(),
                v.getNumHabitaciones(),
                v.getMetrosCuadrados(),
                v.getNumBanios(),
                v.isTienePiscina(),
                v.isTieneAscensor(),
                v.isTieneGaraje()
        );
    }

    public GetViviendaDto viviendaToGetViviendaDto(Vivienda v){
        GetViviendaDto result = new GetViviendaDto();
        result.setId(v.getId());
        result.setTitulo(v.getTitulo());
        result.setDescripcion(v.getDescripcion());
        result.setAvatar(v.getAvatar());
        result.setLating(v.getLating());
        result.setDireccion(v.getDireccion());
        result.setCodigoPostal(v.getCodigoPostal());
        result.setPoblacion(v.getPoblacion());
        result.setProvincia(v.getProvincia());
        result.setTipo(v.getTipo());
        result.setPrecio(v.getPrecio());
        result.setNumHabitaciones(v.getNumHabitaciones());
        result.setMetrosCuadrados(v.getMetrosCuadrados());
        result.setNumBanios(v.getNumBanios());
        result.setTienePiscina(v.isTienePiscina());
        result.setTieneAscensor(v.isTieneAscensor());
        result.setTieneGaraje(v.isTieneGaraje());

        return result;
    }

}
