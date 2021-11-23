package com.triana.realestatev2.users.model;

import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.NaturalId;
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
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Preguntar mañana por el AuditingEntityListener.class
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 6189678452627071360L;

    @Id
    @GeneratedValue
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
    private Inmobiliaria inmobiliaria;


    @OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Interesa> interesa = new ArrayList<>();


    @OneToMany(mappedBy = "propietario")
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
