package com.codegym.service.impl;

import com.codegym.model.Merchant;
import com.codegym.repository.IMerchantRepository;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MerchantService implements IMerchantService {
    @Autowired
    private IMerchantRepository merchantRepository;
    @Override
    public Page<Merchant> findAll(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    @Override
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteById(Long id) {
        merchantRepository.deleteById(id);
    }
}
