package com.triana.realestatev2.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Vivienda {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @Lob
    private String descripcion;

    @Lob
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

    @ManyToOne
    @JoinColumn(name = "id_inmobiliaria")
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.REMOVE)
    private List<Interesa> interesa = new ArrayList<>();

    public Vivienda(String titulo, String descripcion, String avatar, String lating, String direccion,
                    String codigoPostal, String poblacion, String provincia, String tipo, double precio,
                    int numHabitaciones, double metrosCuadrados, int numBanios, boolean TienePiscina,
                    boolean tieneAscensor, boolean tieneGaraje) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.lating = lating;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipo = tipo;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.numBanios = numBanios;
        this.tienePiscina = TienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }

    //TODO :Hacer helpers


}
