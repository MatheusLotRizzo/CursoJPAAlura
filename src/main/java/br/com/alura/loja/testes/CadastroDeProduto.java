package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		//cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = new Produto();
		/*System.out.println(p.getNome());*/
		
		List<Produto> todosProdutos = produtoDao.buscarTodos();
		todosProdutos.forEach(p2 -> System.out.println(p.getNome()));
		
		List<Produto> produtosPorNome = produtoDao.buscarPorNome("S20 FE");
		produtosPorNome.forEach(p2 -> System.out.println(p.getNome()));
		
		List<Produto> produtosPorCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		produtosPorCategoria.forEach(p2 -> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("S20 FE");
		System.out.println("Preco do Produto: " +precoDoProduto);
	}

	@SuppressWarnings("unused")
	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("S20 FE","128 gb 6 gb de Ram", new BigDecimal(2000) ,celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}
