package com.triana.realestatev2.service;

import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.ViviendaDtoConverter;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.repository.InteresaRepository;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InteresaService
        extends BaseService<Interesa, Long , InteresaRepository> {

    private final ViviendaDtoConverter viviendaDtoConverter;

    public List<Usuario> getInteresados(){
        return repositorio.findInteresados();
    }

    public List<Vivienda> topVivienda(){
        return repositorio.top10ViviendasInteresas();
    }

    public List<GetViviendaDto> topViviendaDto(){
        List<GetViviendaDto> listaViviendas = new ArrayList<>();

        topVivienda().stream().forEach(vivienda -> {
            listaViviendas.add(viviendaDtoConverter.viviendaToGetViviendaDto(vivienda));
        });

        return listaViviendas;

    }
}
