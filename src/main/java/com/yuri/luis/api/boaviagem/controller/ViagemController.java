package com.yuri.luis.api.boaviagem.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.luis.api.boaviagem.model.Despesa;
import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.repository.DespesaRepository;
import com.yuri.luis.api.boaviagem.repository.ViagemRepository;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "*")
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@GetMapping
	public List<Viagem> findAll() { 
		
		List<Viagem> lista = viagemRepository.findAll(Sort.by("dataChegada"));
		
		for (Viagem viagem : lista) {
			
			List<Despesa> despesas = viagem.getDespesa();
			
			despesas.sort(Comparator.comparing(Despesa::getTipo));
			
			viagem.setDespesa(despesas);
			
		}
		return lista;
	}
	
	
	@PostMapping("/salvaViagem")
	public Viagem salvar(@RequestBody Viagem v) {
		
		Viagem var = viagemRepository.save(v);
		
		v.setIdViagem(var.getIdViagem());
		
		if (v.getDespesa() != null) {
			
			for (Despesa i : v.getDespesa()) {
				
				i.setIdViagem(var);
				
				;
				despesaRepository.save(i);
			}
		}
		return viagemRepository.findById(var.getIdViagem()).get();
	}
	
	
	@PostMapping("/deletaDespesa/{idviagem}")
	public Viagem deletaDespesa(@PathVariable("idviagem") Integer idviagem, @RequestBody Despesa despesa) {
		
		despesaRepository.deleteById(despesa.getIdDespesa());
		
		return viagemRepository.findById(idviagem).get();
		
	}

	
	@PostMapping("/adicionaDespesa/{idviagem}")
	public ResponseEntity<?> adicionaDespesa(@PathVariable("idviagem") Integer idViagem, @RequestBody Despesa despesa) {
		
		Viagem viagem = viagemRepository.findById(idViagem).get();
		
		despesa.setIdViagem(viagem);
		
		despesaRepository.save(despesa);
		
		Optional<Viagem> r = viagemRepository.findById(idViagem);
		
		return ResponseEntity.ok(r.get());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		
		Optional<Viagem> viagem = viagemRepository.findById(id);
		if (viagem.isPresent()) {
			return ResponseEntity.ok(viagem.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteViagem(@PathVariable("id") Integer id) {
		
		Viagem viagem = viagemRepository.findById(id).get();
		
		for (Despesa i : viagem.getDespesa()) {
			
			despesaRepository.deleteById(i.getIdDespesa());
			
		}
		viagem = viagemRepository.findById(id).get();
		
		viagemRepository.deleteById(id);
	}

	@GetMapping("/viagemPorUsuario/{idLogin}")
	public List<Viagem> viagemPorUsuario(@PathVariable("idLogin") Integer idLogin) {
		
		List<Viagem> list = viagemRepository.findAll(Sort.by("dataChegada"));
		
		ArrayList<Viagem> listViagem = new ArrayList<Viagem>();
		
		for (Viagem v : list) {
			
			if(v.getIdLogin().getIdLogin() == idLogin) {
				
				List<Despesa> gastos = v.getDespesa();
				
				gastos.sort(Comparator.comparing(Despesa::getData));
				
				v.setDespesa(gastos);
				
				listViagem.add(v);
			}
		}
		return listViagem;
	}
	
}
