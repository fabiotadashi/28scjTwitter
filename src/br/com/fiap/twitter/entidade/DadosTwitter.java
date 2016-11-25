package br.com.fiap.twitter.entidade;

import java.time.LocalDateTime;

public class DadosTwitter {
	private long id;
	private String autor;
	private String nome;
	private String sobrenome;
	private LocalDateTime data;
	private int qtdeRetweets;
	private int qtdeFovoritacao;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public int getQtdeRetweets() {
		return qtdeRetweets;
	}
	public void setQtdeRetweets(int qtdeRetweets) {
		this.qtdeRetweets = qtdeRetweets;
	}
	public int getQtdeFovoritacao() {
		return qtdeFovoritacao;
	}
	public void setQtdeFovoritacao(int qtdeFovoritacao) {
		this.qtdeFovoritacao = qtdeFovoritacao;
	}
	
	
	

}
