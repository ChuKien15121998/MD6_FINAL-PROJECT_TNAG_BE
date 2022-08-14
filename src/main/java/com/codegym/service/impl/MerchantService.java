package com.codegym.service.impl;

import com.codegym.model.AppUser;
import com.codegym.model.Cart;
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
    IMerchantRepository merchantRepository;

    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public void remove(Long id) {
        merchantRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        merchantRepository.deleteById(id);
    }

    public Optional<Merchant> findMerchantByAppUser(AppUser appUser) {
        return merchantRepository.findMerchantByAppUser(appUser);
    }

    public Iterable<Merchant> getListMerchantInCart(Cart cart) {
        return merchantRepository.getListMerchantInCart(cart.getId());
    }

    public Iterable<Merchant> findMerchantByGoldPartnerTrue() {
        return merchantRepository.findMerchantByGoldPartnerTrue();
    }

    @Override
    public Page<Merchant> findAll(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }
}
