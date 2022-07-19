package com.codegym.service.impl;

import com.codegym.model.Merchant;
import com.codegym.repository.IMerchantRepository;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    IMerchantRepository merchantRepository;

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

//    public Optional<Merchant> findMerchantByUser_Id(Long id) {
//        return merchantRepository.findMerchantByUser_Id(id);
//    }

    public Iterable<Merchant> findMerchantByNameContaining(String name) {
        return merchantRepository.findMerchantByNameContaining(name);
    }

    public Optional<Merchant> findMerchantByAppUser_Id(Long id) {
        return merchantRepository.findMerchantByAppUser_Id(id);
    }
}
