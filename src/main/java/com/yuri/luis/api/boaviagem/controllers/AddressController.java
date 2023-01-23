package com.yuri.luis.api.boaviagem.controllers;

import com.yuri.luis.api.boaviagem.models.Address;
import com.yuri.luis.api.boaviagem.models.response.AddressDTO;
import com.yuri.luis.api.boaviagem.services.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressDTO addressDTO) {
        var address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.addressService.add(address));
    }
}
