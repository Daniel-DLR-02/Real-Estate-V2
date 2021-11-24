package com.triana.realestatev2.dto.InmobiliariaDto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInmobiliariaDto {

    private String nombre , email, telefono;
}
