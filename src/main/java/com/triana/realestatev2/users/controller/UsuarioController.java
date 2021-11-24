package com.triana.realestatev2.users.controller;

import com.triana.realestatev2.users.dto.*;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/auth/register/user")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto nuevoUsuario){
        Usuario saved = usuarioService.saveProp(nuevoUsuario);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioDto(saved));
    }

    @GetMapping("/propietario/")
    public ResponseEntity<List<Usuario>> getPropietarios(){
        List<Usuario> listaPropietarios = usuarioService.loadUserByRol(UsuarioRole.PROPIETARIO);

        return ResponseEntity.ok(listaPropietarios);
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
    public ResponseEntity<GetUsuarioPropietarioDto> findProp(@PathVariable UUID id, Authentication authentication) {

        Optional<Usuario> usuarioBuscado = usuarioService.findById(id);


        if (usuarioBuscado.isPresent()){
            if (authentication.equals(usuarioBuscado) || authentication.equals(UsuarioRole.ADMIN)) {
                return ResponseEntity.ok(usuarioDtoConverter.usuarioToGetUsuarioPropietarioDto(usuarioBuscado.get()));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
