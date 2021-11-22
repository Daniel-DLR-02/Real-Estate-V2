package com.triana.realestatev2.users.controller;

import com.triana.realestatev2.users.dto.CreateUsuarioDto;
import com.triana.realestatev2.users.dto.GetUsuarioDto;
import com.triana.realestatev2.users.dto.UsuarioDtoConverter;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/auth/register/gestor")
    public ResponseEntity<GetUsuarioDto> nuevoGestor(@RequestBody CreateUsuarioDto nuevoUsuario){
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

}
