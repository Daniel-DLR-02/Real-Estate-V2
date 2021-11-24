package com.triana.realestatev2.dto.ViviendaDto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetViviendaPropietarioDto {

    private String descripcion;
    private String titulo;
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
    private String nombrePropietario;
    private String apellidosPropietario;
    private String emailPropietario;
    private String telefonoPropietario;

}
