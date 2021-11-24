package com.triana.realestatev2.users.services;

import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.security.jwt.JwtAuthorizationFilter;
import com.triana.realestatev2.security.jwt.JwtProvider;
import com.triana.realestatev2.service.InmobiliariaService;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.dto.CreateUsuarioDto;
import com.triana.realestatev2.users.dto.CreateUsuarioGestorDto;
import com.triana.realestatev2.users.dto.GetUsuarioPropietarioDto;
import com.triana.realestatev2.users.dto.UsuarioDtoConverter;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userDetailService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;
    private final UsuarioDtoConverter usuarioDtoConverter;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email+ "no encontrado"));
    }

    public Usuario saveProp(CreateUsuarioDto nuevoUser){
        //Hacer excepción de correo repetido
        if (nuevoUser.getPassword().contentEquals(nuevoUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .nombre(nuevoUser.getNombre())
                    .apellidos(nuevoUser.getApellidos())
                    .direccion(nuevoUser.getDireccion())
                    .email(nuevoUser.getEmail())
                    .telefono(nuevoUser.getTelefono())
                    .avatar(nuevoUser.getAvatar())
                    .password(passwordEncoder.encode(nuevoUser.getPassword()))
                    .role(UsuarioRole.PROPIETARIO)
                    .build();

            return save(usuario);
        }else{
            return null;
        }


    }

    public Usuario saveGestor(CreateUsuarioGestorDto nuevoUser){

        if (nuevoUser.getPassword().contentEquals(nuevoUser.getPassword2())) {


            Usuario usuario = Usuario.builder()
                    .nombre(nuevoUser.getNombre())
                    .apellidos(nuevoUser.getApellidos())
                    .direccion(nuevoUser.getDireccion())
                    .email(nuevoUser.getEmail())
                    .telefono(nuevoUser.getTelefono())
                    .avatar(nuevoUser.getAvatar())
                    .password(passwordEncoder.encode(nuevoUser.getPassword()))
                    .role(UsuarioRole.GESTOR)
                    .inmobiliaria(null)
                    .build();

            Optional<Inmobiliaria> inmobiliariaBuscada = inmobiliariaService.findById(nuevoUser.getIdInmobiliaria());

            if(inmobiliariaBuscada.isPresent())
                usuario.addInmobiliaria(inmobiliariaBuscada.get());

            return save(usuario);
        }else{
            return null;
        }



    }

    public List<Usuario> loadUserByRol(UsuarioRole role) throws UsernameNotFoundException {
        return this.repositorio.findByRole(role);
    }


    public Usuario saveAdmin(CreateUsuarioDto nuevoUser){

        if (nuevoUser.getPassword().contentEquals(nuevoUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .nombre(nuevoUser.getNombre())
                    .apellidos(nuevoUser.getApellidos())
                    .direccion(nuevoUser.getDireccion())
                    .email(nuevoUser.getEmail())
                    .telefono(nuevoUser.getTelefono())
                    .avatar(nuevoUser.getAvatar())
                    .password(passwordEncoder.encode(nuevoUser.getPassword()))
                    .role(UsuarioRole.ADMIN)
                    .build();
            return save(usuario);
        }else{
            return null;
        }

    }




}
