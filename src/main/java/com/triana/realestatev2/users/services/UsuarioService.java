package com.triana.realestatev2.users.services;

import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.dto.CreateUsuarioDto;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email+ "no encontrado"));
    }

    public Usuario saveProp(CreateUsuarioDto nuevoUser){

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

    public Usuario saveGestor(CreateUsuarioDto nuevoUser){

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
                    .build();
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
