package com.triana.realestatev2.controller;

import com.triana.realestatev2.dto.InmobiliariaDto.CreateInmobiliariaDto;
import com.triana.realestatev2.dto.InmobiliariaDto.GetInmobiliariaDto;
import com.triana.realestatev2.dto.InmobiliariaDto.InmobiliariaDtoConverter;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.service.InmobiliariaService;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inmobiliaria")
@RequiredArgsConstructor
@CrossOrigin
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter dtoConverter;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateInmobiliariaDto inmoCreate, @AuthenticationPrincipal Usuario user){

        if(user.getRole().equals(UsuarioRole.ADMIN)){
            Inmobiliaria inmoCreada=dtoConverter.createInmobiliariaDtoToInmobiliaria(inmoCreate);
            inmobiliariaService.save(inmoCreada);
            return ResponseEntity.ok(dtoConverter.inmobiliariaToGetInmobiliariaDto(inmoCreada));
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<GetInmobiliariaDto>> findAll(){

        List<GetInmobiliariaDto> listaInmo = new ArrayList<>();

        inmobiliariaService.findAll().stream().forEach(i ->{
            listaInmo.add(dtoConverter.inmobiliariaToGetInmobiliariaDto(i));
        });

        return ResponseEntity.ok(listaInmo);
    }
}
