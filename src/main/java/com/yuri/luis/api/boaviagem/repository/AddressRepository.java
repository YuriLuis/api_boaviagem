package com.yuri.luis.api.boaviagem.repository;

import com.yuri.luis.api.boaviagem.model.Address;
import com.yuri.luis.api.boaviagem.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
