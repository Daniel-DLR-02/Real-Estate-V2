package com.triana.realestatev2.dto.ViviendaDto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetViviendaDto {


    private Long id;
    private String descripcion;
    private String titulo;
    private String avatar;
    private String lating;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;
    private String tipo;
    private double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanios;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
}
