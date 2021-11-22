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

@Service("userDetailService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email+ "no encontrado"));
    }//Preguntar mañana

    public Usuario saveProp(CreateUsuarioDto nuevoUser){
        //Preguntar mañana por la validación de contraseñas
        //Preguntar mañana sobre el rol inicial a asignar
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
        //Preguntar mañana por la validación de contraseñas
        //Preguntar mañana sobre el rol inicial a asignar
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

    public Usuario saveAdmin(CreateUsuarioDto nuevoUser){
        //Preguntar mañana por la validación de contraseñas
        //Preguntar mañana sobre el rol inicial a asignar
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
