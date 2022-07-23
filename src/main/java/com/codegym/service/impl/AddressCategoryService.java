package com.codegym.service.impl;

import com.codegym.model.AddressCategory;
import com.codegym.repository.IAddressCategoryRepository;
import com.codegym.service.IAddressCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddressCategoryService implements IAddressCategoryService {
    @Autowired
    IAddressCategoryRepository addressCategoryRepository;

    public Iterable<AddressCategory> findAll() {
        return addressCategoryRepository.findAll();
    }

    public Optional<AddressCategory> findById(Long id) {
        return addressCategoryRepository.findById(id);
    }

    public AddressCategory save(AddressCategory addressCategory) {
        return addressCategoryRepository.save(addressCategory);
    }

    public void remove(Long id) {
        addressCategoryRepository.deleteById(id);
    }
}
