package com.triana.realestatev2.users.controller;

import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.dto.*;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.triana.realestatev2.users.model.UsuarioRole.PROPIETARIO;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;
    private final ViviendaService viviendaService;
    @PostMapping("/auth/register/user")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto nuevoUsuario){
        Usuario saved = usuarioService.saveProp(nuevoUsuario);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioDto(saved));
    }

    @GetMapping("/propietario/")
    public ResponseEntity<List<GetUsuarioDto>> getPropietarios(){
        List<GetUsuarioDto> listaPropietarios = new ArrayList<>();
        usuarioService.findProps().stream().forEach(v->{
            listaPropietarios.add(usuarioDtoConverter.usuarioToGetUsuarioDto(v));
        });
        return ResponseEntity.ok(listaPropietarios);
    }
    @GetMapping("/vivienda/enpropiedad")
    public ResponseEntity<List<GetViviendaDto>> getViviendasPropietario(@AuthenticationPrincipal Usuario user){
        Optional<Usuario> propietario = usuarioService.findById(user.getId());

        if(propietario.isPresent()) {
            return ResponseEntity.ok(viviendaService.getViviendaDtoListProp(propietario.get()));
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUsuarioDto> nuevoGestor(@RequestBody CreateUsuarioGestorDto nuevoUsuario){
        Usuario saved = usuarioService.saveGestor(nuevoUsuario);
        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioDto(saved));
    }


    @PostMapping("/auth/register/admin")
    public ResponseEntity<GetUsuarioDto> nuevoAdmin(@RequestBody CreateUsuarioDto nuevoUsuario){
        Usuario saved = usuarioService.saveAdmin(nuevoUsuario);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioDto(saved));
    }


    @GetMapping("/propietario/{id}")
    public ResponseEntity<?> findProp(@PathVariable UUID id, @AuthenticationPrincipal Usuario userActual) {

        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);

        if (usuarioBuscado.isPresent()){
            if (userActual.getId().equals(usuarioBuscado.get().getId()) || userActual.getRole().equals(UsuarioRole.ADMIN)) {
                return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioPropietarioDto(usuarioBuscado.get()));
            } else {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.FORBIDDEN);
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("propietario/{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable UUID id, @AuthenticationPrincipal Usuario userActual) {

        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);

        if (usuarioBuscado.isPresent()){
            if (userActual.getId().equals(usuarioBuscado.get().getId()) || userActual.getRole().equals(UsuarioRole.ADMIN)) {
                usuarioService.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.FORBIDDEN);
            }
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

}
