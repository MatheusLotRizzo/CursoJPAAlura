package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		//cadastrarCliente();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorID(19l);
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorID(1l);
		
		PedidoDao pedidoDao = new PedidoDao(em);
		
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.AdicionarItem(new ItemPedido(10, pedido, produto));

		//PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido(); 
		System.out.println("Valo Total Vendido R$ " +totalVendido);
	}
	
	
	public static void cadastrarCliente() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente cliente = new Cliente("Matheus", "123456");
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}
}
