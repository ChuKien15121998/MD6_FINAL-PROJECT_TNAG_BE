package com.codegym.service.impl;

import com.codegym.model.MerchantRegisterRequest;
import com.codegym.repository.IMerchantRegisterRepository;
import com.codegym.service.IMerchantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantRegisterRequestService implements IMerchantRegisterService {
    @Autowired
    IMerchantRegisterRepository merchantRegisterRepository;


    public Iterable<MerchantRegisterRequest> findAll() {
        return merchantRegisterRepository.findAll();
    }

    public Optional<MerchantRegisterRequest> findById(Long id) {
        return merchantRegisterRepository.findById(id);
    }

    public MerchantRegisterRequest save(MerchantRegisterRequest merchantRegisterRequest) {
        return merchantRegisterRepository.save(merchantRegisterRequest);
    }

    public void remove(Long id) {
        merchantRegisterRepository.deleteById(id);
    }

    public Optional<MerchantRegisterRequest> findMerchantRegisterRequestByUsernameAndReviewed(String username, boolean reviewed) {
        return merchantRegisterRepository.findMerchantRegisterRequestByUsernameAndReviewed(username, reviewed);
    }

    public Iterable<MerchantRegisterRequest> findMerchantByReviewed(boolean reviewed) {
        return merchantRegisterRepository.findMerchantByReviewed(reviewed);
    }
}
