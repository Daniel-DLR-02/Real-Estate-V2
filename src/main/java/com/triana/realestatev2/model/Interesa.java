package com.triana.realestatev2.model;

import com.triana.realestatev2.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Interesa {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name="vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("interesado_id")
    @JoinColumn(name="interesado_id")
    private Usuario interesado;

    private LocalDate createdDate;

    @Lob
    private String mensaje;


    public void addInteresado(Usuario newInteresado) {
        this.interesado=newInteresado;
        newInteresado.getInteresa().add(this);
    }

    public void addVivienda(Vivienda newVivienda) {
        this.vivienda=newVivienda;
        newVivienda.getInteresa().add(this);
    }
}
