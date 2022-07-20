package com.codegym.repository;


import com.codegym.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?

    Boolean existsByUsername(String username);

    Optional<AppUser> findById(Long id);

}
