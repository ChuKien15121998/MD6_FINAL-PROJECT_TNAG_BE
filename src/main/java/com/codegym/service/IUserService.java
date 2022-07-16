package com.codegym.service;

import com.codegym.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGeneralService<Users> {
    Optional<Users> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    Iterable<Users> findAllByNameContaining(String name);
    Boolean checkExistsByUsername(String username);
    Boolean checkExistsByEmail(String email);
    Iterable<Users> listExistsByUsername(String username);

    Page<Users> findAll(Pageable pageable);

    Optional<Users> findById(Long id);
    Users findByEmail(String email);

//    Users findByVerificationCode(String code);
//    void sendVerificationEmail(Users users, String siteURL) throws MessagingException, UnsupportedEncodingException;
//    Boolean verify(String verificationCode);
//    Iterable<Users> getNext3User(int row);
//    Iterable<Users> getTop3();

}
