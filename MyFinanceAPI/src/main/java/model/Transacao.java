package model;

import java.time.LocalDate;

public class Transacao {
	private int id;
	private String descricao;
	private double valor;
	private String tipo;
	private String categoria;
	private LocalDate data;

	public Transacao() {}

	public Transacao(int id, String descricao, double valor, String tipo, String categoria, LocalDate data) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo = tipo;
		this.categoria = categoria;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
