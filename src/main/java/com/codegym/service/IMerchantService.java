package com.codegym.service;

import com.codegym.model.Merchant;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant> {
    Iterable<Merchant> findMerchantByNameContaining(String name);
    Optional<Merchant> findMerchantByAppUser_Id(Long id);

}
