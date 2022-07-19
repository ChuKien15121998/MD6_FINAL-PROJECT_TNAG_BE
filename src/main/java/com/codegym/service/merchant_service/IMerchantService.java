package com.codegym.service.merchant_service;

import com.codegym.model.Merchant;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant> {
    Iterable<Merchant> findAll();

    Optional<Merchant> findById(Long id);

    Merchant save(Merchant merchant);

    void remove(Long id);
}
