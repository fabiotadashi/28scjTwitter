package br.com.fiap.twitter.entidade;

import java.time.LocalDateTime;


/**
 * @author Douglas
 *
 *	Classe que representa os dados (Status) do twitter
 */
public class DadosTwitter {
	private long id;
	private String autor;
	private String nome;
	private LocalDateTime data;
	private String mensagem;

	public DadosTwitter(long id, String autor, String nome, LocalDateTime data, String mensagem) {
		this.id = id;
		this.autor = autor;
		this.nome = nome;
		this.data = data;
		this.mensagem = mensagem;
	}

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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return this.getId() + " - " + this.getAutor() + " - " + this.getNome()+" - "+this.getData();
	}

}
