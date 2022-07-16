package com.codegym.service;

import com.codegym.model.Role;
import com.codegym.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
