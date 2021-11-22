package com.triana.realestatev2.users.services;

import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.UserEntity;
import com.triana.realestatev2.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {

    private final PasswordEncoder passwordEncoder;


    public UserEntity nuevoUsuario(CreateUserDto nuevoUser){

    }
}
