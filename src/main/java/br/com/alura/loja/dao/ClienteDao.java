package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}
	
	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}
	
	public Cliente buscarPorID(Long id) {
		return em.find(Cliente.class, id);
	}

}
