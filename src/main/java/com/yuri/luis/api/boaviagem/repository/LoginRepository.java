package com.yuri.luis.api.boaviagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.luis.api.boaviagem.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

}
