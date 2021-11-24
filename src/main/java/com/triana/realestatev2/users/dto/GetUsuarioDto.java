package com.triana.realestatev2.users.dto;

import com.triana.realestatev2.users.model.UsuarioRole;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class GetUsuarioDto {

    private UUID id;
    private String nombre;
    private String email;
    private String avatar;
    private UsuarioRole role;

}
