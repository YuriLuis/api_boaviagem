package com.yuri.luis.api.boaviagem.controller;

import com.yuri.luis.api.boaviagem.model.Address;
import com.yuri.luis.api.boaviagem.model.Despesa;
import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.repository.AddressRepository;
import com.yuri.luis.api.boaviagem.repository.DespesaRepository;
import com.yuri.luis.api.boaviagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;


    @GetMapping
    public List<Address> findAll() {
        return addressRepository.findAll();
    }


    @PostMapping("/insertAddress")
    public Address salvar(@RequestBody Address address) {

        Address var = addressRepository.save(address);

        address.setIdAddress(var.getIdAddress());

        return addressRepository.findById(var.getIdAddress()).get();
    }


    @PostMapping("/deletaAddress/{idViagem}")
    public Address deletaAddress(@PathVariable("idViagem") Integer id, @RequestBody Address address) {

        addressRepository.delete(address);

        return addressRepository.findById(id).get();

    }
}
