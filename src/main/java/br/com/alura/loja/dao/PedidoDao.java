package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

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
		return em.createQuery(query.toString(), BigDecimal.class).getSingleResult();
	}

	public List<RelatorioDeVendasVo> relatorioDeVendas() {

		StringBuilder query = new StringBuilder();

		query.append("select new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, ");
		query.append("SUM(item.quantidade), ");
		query.append("MAX(pedido.data) ) ");
		query.append("FROM Pedido pedido ");
		query.append("join pedido.itens item ");
		query.append("join item.produto produto ");
		query.append("group by produto.nome ");
		query.append("order by item.quantidade DESC ");

		return em.createQuery(query.toString(), RelatorioDeVendasVo.class).getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("select p from Pedido p join fetch p.cliente where p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
