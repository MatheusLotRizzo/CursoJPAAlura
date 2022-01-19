package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorID(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		//String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createNamedQuery("Produto.produtosPorNome", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT p FROM Produto p ");
		jpql.append("WHERE p.categoria.nome = :nome");
		
		return em.createQuery(jpql.toString(), Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametro(String nome, BigDecimal preco, 
			LocalDate dataCadastro){
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("select p from produto p where 1=1");
		
		if(!nome.trim().isEmpty() && nome != null) {
			jpql.append("and p.nome = :nome");
		}
		
		if(preco != null) {
			jpql.append("and p.preco = :preco");
		}
		
		if(dataCadastro != null) {
			jpql.append("where p.dataCadastro = :dataCadastro");
		}
		
		TypedQuery<Produto> query =  em.createQuery(jpql.toString(), Produto.class);
		
		if(!nome.trim().isEmpty() && nome != null) {
			query.setParameter("nome", nome);
		}
		
		if(preco != null) {
			query.setParameter("preco", preco);
		}
		
		if(dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		
		return query.getResultList();
	}
	
	public List<Produto> buscarPorParametroCriteriaApi(String nome, BigDecimal preco, 
			LocalDate dataCadastro){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if(!nome.trim().isEmpty() && nome != null) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		
		if(preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		
		if(dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
	
	
}
