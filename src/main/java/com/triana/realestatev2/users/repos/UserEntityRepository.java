package com.triana.realestatev2.users.repos;

import com.triana.realestatev2.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
