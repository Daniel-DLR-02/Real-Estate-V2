package com.triana.realestatev2.controller;

import com.triana.realestatev2.dto.InmobiliariaDto.*;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.service.InmobiliariaService;
import com.triana.realestatev2.users.dto.GetUsuarioDto;
import com.triana.realestatev2.users.dto.UsuarioDtoConverter;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/inmobiliaria")
@RequiredArgsConstructor
@CrossOrigin
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter dtoConverter;
    private final UsuarioDtoConverter userDtoConverter;
    private final UsuarioService usuarioService;
    private final GestorConverter gestorConverter;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateInmobiliariaDto inmoCreate, @AuthenticationPrincipal Usuario user){

        if(user.getRole().equals(UsuarioRole.ADMIN)){
            Inmobiliaria inmoCreada=dtoConverter.createInmobiliariaDtoToInmobiliaria(inmoCreate);
            inmobiliariaService.save(inmoCreada);
            return ResponseEntity.ok(dtoConverter.inmobiliariaToGetInmobiliariaDto(inmoCreada));
        }
        else{
            return ResponseEntity.status(FORBIDDEN).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<GetInmobiliariaDto>> findAll(){

        List<GetInmobiliariaDto> listaInmo = inmobiliariaService.listaInmoToListaGetInmoDto(inmobiliariaService.findAll());

        return ResponseEntity.ok(listaInmo);
    }

    @GetMapping("/{id}/gestor")
    public ResponseEntity<List<GetUsuarioDto>> listarGestoresInmobiliaria(@PathVariable Long id, @AuthenticationPrincipal Usuario user){

        Optional<Inmobiliaria> inmobiliariaBuscada = inmobiliariaService.findById(id);

        if(inmobiliariaBuscada.isPresent()) {
            if (inmobiliariaService.comprobarGestorPerteneceInmobiliaria(user, inmobiliariaBuscada.get()) || user.getRole().equals(UsuarioRole.ADMIN)) {

                List<GetUsuarioDto> listaGestores = usuarioService.listaUsuarioToListGetUsuarioDto(inmobiliariaBuscada.get().getGestores());

                return ResponseEntity.ok(listaGestores);

            }
            else{
                return ResponseEntity.status(FORBIDDEN).build();
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/gestor")
    public ResponseEntity<GetUsuarioDto> registeGestor(@PathVariable Long id, @RequestBody CreateGestorEnInmoDto gestorCrear, @AuthenticationPrincipal Usuario user){
        Optional<Inmobiliaria> inmobiliariaBuscada = inmobiliariaService.findById(id);

        if(inmobiliariaBuscada.isPresent()){
            if(user.getRole().equals(UsuarioRole.ADMIN) || inmobiliariaService.comprobarGestorPerteneceInmobiliaria(user,inmobiliariaBuscada.get())){
                Usuario usuarioCreado=gestorConverter.createGestorEnInmoDtoToGestor(gestorCrear,inmobiliariaBuscada.get());
                usuarioService.save(usuarioCreado);
                return ResponseEntity.status(CREATED).body(userDtoConverter.usuarioToGetUsuarioDto(usuarioCreado));
            }else{
                return ResponseEntity.status(FORBIDDEN).build();
            }
        }
        else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/gestor/{id}")
    public ResponseEntity<?> removeGestor(@PathVariable UUID id, @AuthenticationPrincipal Usuario userLogged){

        Optional<Usuario> gestor = usuarioService.findById(id);
        Inmobiliaria inmo = userLogged.getInmobiliaria();

        if(gestor.isPresent()){
            if(userLogged.getRole().equals(UsuarioRole.ADMIN) || inmobiliariaService.comprobarGestorPerteneceInmobiliaria(gestor.get(),inmo)){
                usuarioService.deleteById(id);
            }
        }

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeInmo(@PathVariable Long id, @AuthenticationPrincipal Usuario userLogged){
        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);
        if(inmo.isPresent()){
            if(userLogged.getRole().equals(UsuarioRole.ADMIN)) {
                inmo.get().removeInmoFromViviendas();
                inmobiliariaService.deleteById(id);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("inmobiliaria/{id}")
    public ResponseEntity<GetInmobiliariaDto> findOne(@PathVariable Long id, @AuthenticationPrincipal Usuario user){
        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);
        if(inmo.isPresent())
            return ResponseEntity.ok(dtoConverter.inmobiliariaToGetInmobiliariaDto(inmo.get()));
        else
            return ResponseEntity.notFound().build();
    }

}
