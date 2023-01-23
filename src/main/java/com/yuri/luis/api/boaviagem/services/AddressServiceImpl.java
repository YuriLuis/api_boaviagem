package com.yuri.luis.api.boaviagem.services;

import com.yuri.luis.api.boaviagem.models.Address;
import com.yuri.luis.api.boaviagem.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address add(Address address) {
        return addressRepository.save(address);
    }
}
