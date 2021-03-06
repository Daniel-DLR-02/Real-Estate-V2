package com.triana.realestatev2.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="usuario")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre;

    private String apellidos;

    private String direccion;

    @NaturalId
    @Column(unique = true,updatable = false)
    private String email;

    private String telefono;

    private String avatar;

    private String password;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria_id", foreignKey = @ForeignKey(name = "FK_USER_INMOBILIARIA"), nullable = true)
    private Inmobiliaria inmobiliaria;


    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "interesado",fetch = FetchType.EAGER)
    private List<Interesa> interesa = new ArrayList<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.ALL)
    private List<Vivienda> viviendas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public void addInmobiliaria(Inmobiliaria inmo){
        this.inmobiliaria = inmo;
        inmo.getGestores().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria inmo){
        inmo.getGestores().remove(this);
        this.inmobiliaria = null;
    }
}
