package com.codegym.service;

import com.codegym.model.MerchantRegisterRequest;

import java.util.Optional;

public interface IMerchantRegisterService extends IGeneralService<MerchantRegisterRequest> {
    Optional<MerchantRegisterRequest> findMerchantRegisterRequestByUsernameAndReviewed (String username, boolean reviewed);

    Iterable<MerchantRegisterRequest> findMerchantByReviewed(boolean reviewed);

}
