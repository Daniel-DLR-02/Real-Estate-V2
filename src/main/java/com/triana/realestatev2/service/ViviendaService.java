package com.triana.realestatev2.service;

import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.ViviendaDtoConverter;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.repository.ViviendaRepository;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViviendaService
        extends BaseService<Vivienda,Long, ViviendaRepository> {

    private final UsuarioService userService;
    private final ViviendaDtoConverter dtoConverter;

    public List<GetViviendaDto> getViviendaDtoListProp(Usuario user){

        List<GetViviendaDto> listaGetViviendas = new ArrayList<>();
        Optional<Usuario> prop = userService.findById(user.getId());
        if(prop.isPresent()) {
            List<Vivienda> viviendasPropList = prop.get().getViviendas();
            listaGetViviendas = viviendasPropList.stream().map(dtoConverter::viviendaToGetViviendaDto).collect(Collectors.toList());
        }
        return listaGetViviendas;

    }

}
