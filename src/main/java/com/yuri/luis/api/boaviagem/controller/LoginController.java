package com.yuri.luis.api.boaviagem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.luis.api.boaviagem.model.Login;
import com.yuri.luis.api.boaviagem.repository.LoginRepository;

@RestController
@RequestMapping("/logins")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private LoginRepository loginRepository;
	
	
	@GetMapping
	public List<Login> findAll(){
		return loginRepository.findAll();
	}
	
	@PostMapping("/salvaLogin")
	public Login salvaLogin(@RequestBody Login login) {
		return loginRepository.save(login);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Optional<Login> login = loginRepository.findById(id);
		if (login.isPresent()) {
			return ResponseEntity.ok(login.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletaLogin/{id}")
	public void delete(@PathVariable("id") Integer id) {
		loginRepository.deleteById(id);
	}
	
	@PostMapping("/autenticaLogin")
	public Login validaLogin(@RequestBody Login login) {
		Login retorno = new Login();
		List<Login> lista = loginRepository.findAll();
		for (Login l : lista) {
			if (l.getEmail().equalsIgnoreCase(login.getEmail())) {
				if (l.getSenha().equals(login.getSenha())) {
					retorno = l;
				}
				break;
			}
		}
		return retorno;
	}
}
