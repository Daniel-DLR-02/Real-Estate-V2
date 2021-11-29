package com.triana.realestatev2.model;

import com.triana.realestatev2.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Table(name="vivienda")
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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_propietario", foreignKey = @ForeignKey(name = "FK_VIVIENDA_USUARIO"))
    private Usuario propietario;

    @ManyToOne
    @JoinColumn(name = "id_inmobiliaria",nullable = true, foreignKey = @ForeignKey(name = "FK_INMOBILIARIA_USUARIO"))
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
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



    public void addInmobiliaria(Inmobiliaria inmo) {
        inmobiliaria = inmo;
        if (inmo.getViviendas() == null) {
            inmo.setViviendas(new ArrayList());
            inmo.getViviendas().add(this);
        } else {
            inmo.getViviendas().add(this);
        }
    }


    public void removeInmobiliaria() {
        if (this.inmobiliaria != null)
            this.inmobiliaria.getViviendas().remove(this);
        this.setInmobiliaria(null);

    }

    public void addPropietario(Usuario u) {

        this.propietario = u;
        if (u.getViviendas() == null) {
            u.setViviendas(new ArrayList());
            u.getViviendas().add(this);
        } else {
            u.getViviendas().add(this);
        }

    }


    public void removePropietario() {
        if (this.propietario != null)
            this.propietario.getViviendas().remove(this);
        this.setPropietario(null);
    }



}
