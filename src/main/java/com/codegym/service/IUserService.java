package com.codegym.service;

import com.codegym.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGeneralService<AppUser> {
    Optional<AppUser> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Optional<AppUser> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    AppUser save(AppUser appUser);
    Boolean checkExistsByEmail(String email);
    Page<AppUser> findAll(Pageable pageable);
    Optional<AppUser> findById(Long id);
}
