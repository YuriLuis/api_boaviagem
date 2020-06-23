package com.yuri.luis.api.boaviagem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDespesa;
	
	@ManyToOne
	@NotNull
	@JsonIgnore
    private Viagem idViagem;
	
	@NotNull
	private String tipoDespesa;
	
	@NotNull
	private Double valor;
	
	@NotNull
	@Column(length = 20)
    private String data;
	
	@Column(length = 500)
	private String descricao;
	
	@Column(length = 500)
	private String local;

	public Integer getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(Integer idDespesa) {
		this.idDespesa = idDespesa;
	}

	public Viagem getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(Viagem idViagem) {
		this.idViagem = idViagem;
	}

	public String getTipo() {
		return tipoDespesa;
	}

	public void setTipo(String tipo) {
		this.tipoDespesa = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	
}
