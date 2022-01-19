package br.com.alura.loja.testes;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteriaApi {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		//LocalDate data = LocalDate.of(2021, Month.DECEMBER, 21);
		
		/*List<Produto> result = produtoDao.buscarPorParametroCriteriaApi("S20 FE", null, null);
		System.out.println("\n\nRetorno Busca: " + result.toString());*/
	}

}
