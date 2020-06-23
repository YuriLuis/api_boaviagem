package com.yuri.luis.api.boaviagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.luis.api.boaviagem.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

}
