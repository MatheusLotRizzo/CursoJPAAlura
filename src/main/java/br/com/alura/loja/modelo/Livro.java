package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto{
	
	private String autor;
	private int numeroPaginas;
	
	public Livro() {
	}
	
	public Livro(String autor, int numeroPaginas) {
		super();
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	

}
