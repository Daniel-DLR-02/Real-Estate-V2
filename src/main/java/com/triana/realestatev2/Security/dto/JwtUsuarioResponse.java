package com.triana.realestatev2.Security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUsuarioResponse {

    private String email;
    private String nombre;
    private String apellidos;
    private String avatar;
    private String role;
    private String token;
}
