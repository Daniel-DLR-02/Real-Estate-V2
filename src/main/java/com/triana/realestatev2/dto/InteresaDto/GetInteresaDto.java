package com.triana.realestatev2.dto.InteresaDto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetInteresaDto {

    private UUID idInteresado;
    private Long idVivienda;
    private String mensaje;
    private LocalDate createdDate;
}
