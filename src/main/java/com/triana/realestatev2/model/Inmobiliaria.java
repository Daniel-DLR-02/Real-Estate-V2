package com.triana.realestatev2.model;

import com.triana.realestatev2.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor  @NoArgsConstructor
@Getter @Setter
@Builder
public class Inmobiliaria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String email;
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria", fetch = FetchType.EAGER)
    private List<Usuario> gestores = new ArrayList<>();

    public Inmobiliaria(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void removeInmoFromViviendas() {
        viviendas.forEach(v ->
                v.setInmobiliaria(null));
    }

    public void addGestor(Usuario gestor) {
        this.getGestores().add(gestor);
        gestor.setInmobiliaria(this);
    }

    public void removeGestor(Usuario gestor) {
        gestor.setInmobiliaria(null);
        this.getGestores().add(gestor);
    }

}


