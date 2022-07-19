package com.codegym.service.merchant_service;

import com.codegym.model.Merchant;
import com.codegym.repository.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {

    @Autowired
    private IMerchantRepository merchantRepository;

    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public void remove(Long id) {
        merchantRepository.deleteById(id);
    }
}
