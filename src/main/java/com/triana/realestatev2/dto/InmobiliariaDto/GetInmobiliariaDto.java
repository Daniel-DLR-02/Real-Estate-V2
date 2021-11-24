package com.triana.realestatev2.dto.InmobiliariaDto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {
    private Long id;
    private String nombre , email , telefono;
}