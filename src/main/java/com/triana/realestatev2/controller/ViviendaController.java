package com.triana.realestatev2.controller;

import com.triana.realestatev2.dto.ViviendaDto.CreateViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.ViviendaDtoConverter;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final ViviendaDtoConverter dtoConverter;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateViviendaDto viviendaACrear, @AuthenticationPrincipal Usuario propietarioVivienda){

        if (propietarioVivienda.getRole().equals(UsuarioRole.PROPIETARIO)) {

            Vivienda viv = dtoConverter.createViviendaDtoToVivienda(viviendaACrear);
            viv.addPropietario(propietarioVivienda);
            viviendaService.save(viv);
            return ResponseEntity.ok(dtoConverter.viviendaToGetViviendaDto(viv));
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }



    }

    @GetMapping("/")
    public ResponseEntity<List<GetViviendaDto>> findAll(){

        List<GetViviendaDto> listaADevolver = new ArrayList<>();

        viviendaService.findAll().stream().forEach(v->{
            listaADevolver.add(dtoConverter.viviendaToGetViviendaDto(v));
        });

        return ResponseEntity.ok(listaADevolver);

    }

}
