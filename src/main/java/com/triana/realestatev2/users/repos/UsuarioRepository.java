package com.triana.realestatev2.users.repos;

import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByEmail(String email);

    List<Usuario> findByRole (UsuarioRole role);

    Optional<Usuario> findById(UUID id);
}
