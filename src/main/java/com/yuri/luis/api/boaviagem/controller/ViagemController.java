package com.yuri.luis.api.boaviagem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
	
	@PostMapping("/adicionaDespesa/{idviagem}/{idDespesa}")
	public ResponseEntity<?> atualizaDespesa(@PathVariable("idviagem") Integer idViagem, @RequestBody Despesa despesa) {
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

	
	
	@GetMapping("/ViagensNoPeriodo/{data}/{id_login}")
	public List<Viagem> findViagensNoPeriodo(@PathVariable("data") String data,
			@PathVariable("idusuario") Integer idusuario) {
		
		List<Viagem> lista = viagemRepository.findAll(Sort.by("dataPartida"));
		ArrayList<Viagem> retorno = new ArrayList<Viagem>();

		Date dataFiltro = StringToDate(data);
		if (dataFiltro != null) {
			for (Viagem viagem : lista) {
				if (viagem.getIdLogin().getIdLogin() == idusuario) {
					List<Despesa> despesas = viagem.getDespesa();
					despesas.sort(Comparator.comparing(Despesa::getTipo));
					viagem.setDespesa(despesas);
					if (viagem.getDataChegada() != null) {
						Date dataChegada = StringToDate(viagem.getDataChegada());
						if (dataChegada != null) {
							if ((dataChegada.after(dataFiltro)) || (dataChegada.equals(dataFiltro))) {
								if (viagem.getDataPartida() != null) {
									Date dataPartida = StringToDate(viagem.getDataPartida());
									if (dataPartida != null) {
										if ((dataPartida.before(dataFiltro)) || (dataPartida.equals(dataFiltro))) {
											retorno.add(viagem);
										}
									}
								} else {
									retorno.add(viagem);
								}

							}
						}
					}
				}
			}
		}
		return retorno;
	}
	
	private Date StringToDate(String dateStr) {
		try {
			return (new SimpleDateFormat("dd/MM/yyyy")).parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}
}
