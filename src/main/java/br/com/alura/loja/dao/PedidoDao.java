package br.com.alura.loja.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;

public class PedidoDao {
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void atualizar(Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.remove(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		StringBuilder query = new StringBuilder();
		query.append("select sum(p.valorTotal) from Pedido p");
		return em.createQuery(query.toString(), BigDecimal.class)
				.getSingleResult();
	}

}
