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

    Optional<AppUser> findByEmail(String email); //Tim kiem User co ton tai trong DB khong?

    Boolean existsByEmail(String email);

    Optional<AppUser> findById(Long id);

    @Query(value = "select * from users where email not like :email", nativeQuery = true)
    Iterable<AppUser> checkExistsByEmail(String email);
}
