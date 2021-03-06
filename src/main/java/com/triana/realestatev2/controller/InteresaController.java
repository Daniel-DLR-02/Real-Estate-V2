package com.triana.realestatev2.controller;

import com.triana.realestatev2.dto.InteresaDto.CreateInteresaDto;
import com.triana.realestatev2.dto.InteresaDto.GetInteresaDto;
import com.triana.realestatev2.dto.InteresaDto.InteresaDtoConverter;
import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.InteresaService;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.dto.GetUsuarioDto;
import com.triana.realestatev2.users.dto.UsuarioDtoConverter;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InteresaController {

    private final ViviendaService viviendaService;
    private final UsuarioService usuarioService;
    private final InteresaService interesaService;
    private final InteresaDtoConverter interesaDtoConverter;
    private final UsuarioDtoConverter userDtoConverter;

    @PostMapping("vivienda/{id}/meinteresa")
    public ResponseEntity<GetInteresaDto> createInteresa (@RequestBody CreateInteresaDto interesaDto, @PathVariable Long id, @AuthenticationPrincipal Usuario userLogged){

        Optional<Vivienda> vivienda=viviendaService.findById(id);
        if(vivienda.isPresent()) {
            if (userLogged.getRole().equals(UsuarioRole.PROPIETARIO)) {
                Interesa interesa = interesaDtoConverter.createInteresaDtoToInteresa(interesaDto, userLogged, vivienda.get());

                interesa.addInteresado(userLogged);
                interesa.addVivienda(vivienda.get());

                interesaService.save(interesa);
                userLogged.getInteresa().add(interesa);
                usuarioService.save(userLogged);
                return ResponseEntity
                        .status(HttpStatus.CREATED).body(interesaDtoConverter.InteresaToGetInteresaDto(interesa));
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("vivienda/{id}/meinteresa")
    public ResponseEntity<?> deleteInteresa(@PathVariable Long id,@AuthenticationPrincipal Usuario user){

        Optional<Vivienda> viviendaBuscada = viviendaService.findById(id);

        if(viviendaBuscada.isPresent()){

            List<Interesa> listaIntereses = viviendaBuscada.get().getInteresa();
            for (Interesa inte : listaIntereses) {
                if (inte.getInteresado().getId().equals(user.getId())) {
                    interesaService.delete(inte);
                }
            }

        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/interesado/")
    public ResponseEntity<List<GetUsuarioDto>> getInteresados(){

        List<Usuario> interesados = usuarioService.findProps();

        return ResponseEntity.ok().body(usuarioService.listaUsuarioToListGetUsuarioDto(interesados));
    }

    @GetMapping("/interesado/{id}")
    public ResponseEntity<GetUsuarioDto> findOne(@PathVariable UUID id, @AuthenticationPrincipal Usuario user){

        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);

        if(usuarioBuscado.isPresent()){
            if(user.getId().equals(usuarioBuscado.get().getId()) || user.getRole().equals(UsuarioRole.ADMIN)){
                return ResponseEntity.ok(userDtoConverter.usuarioToGetUsuarioDto(usuarioBuscado.get()));
            }
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vivienda/top10")
    public ResponseEntity<List<GetViviendaDto>> viviendasConMasInteresas(){
        return ResponseEntity.ok(interesaService.topViviendaDto());
    }


}
