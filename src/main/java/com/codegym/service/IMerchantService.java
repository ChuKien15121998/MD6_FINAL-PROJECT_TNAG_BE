package com.codegym.service;

import com.codegym.model.Merchant;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant> {
//    Optional<Merchant> findMerchantByUser_Id(Long id);
    Iterable<Merchant> findMerchantByNameContaining(String name);
    Optional<Merchant> findMerchantByAppUser_Id(Long id);

}
