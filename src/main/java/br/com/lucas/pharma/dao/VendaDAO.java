package br.com.lucas.pharma.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lucas.pharma.domain.ItemVenda;
import br.com.lucas.pharma.domain.Produto;
import br.com.lucas.pharma.domain.Venda;
import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa um DAO Venda que extende da classe Generica DAO.
 * 
 */
public class VendaDAO extends GenericDAO<Venda> {

	/**
	 * Método para salvar uma venda na database do banco de dados.
	 * 
	 * @param venda a venda a ser salva no banco de dados.	
	 * @param itensVenda os itensVenda a serem salvos no banco de dados.
	 */
	public void salvar(Venda venda, List<ItemVenda> itensVenda) { 
		// 1ºparam - irá salvar/receber a venda(este param não possui a chave primária) / 2ºparam - irá receber uma lista de itens da venda (percorre a venda adicionando os produtos)

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;//Transaction - para incluir, editar ou excluir é recomendavel utilizar transações
		// Se a transação não funcionar (ocorrer um erro ao salvar), será desfeita a transação(rollback) assim não permitindo que ocorra erro

		try {
			transacao = sessao.beginTransaction(); //iniciar a transação

			sessao.save(venda); // salvar 1 venda

			for(int posicao = 0; posicao < itensVenda.size(); posicao++) { // iterando todos os itensVenda da Venda
				ItemVenda itemVenda = itensVenda.get(posicao); // capturar um itemVenda da linha corrente da lista
				itemVenda.setVenda(venda); // setar a venda com cada itemVenda

				sessao.save(itemVenda); // para 1 venda ele irá salvar n itens (itensVenda)

				// baixar o estoque da venda - produto (dentro do produto é a quantidade em estoque) - itemVenda (dentro do itemVenda é a quantidade vendida)
				Produto produto = itemVenda.getProduto(); // isolar o produto recebendo a quantidade do itemVenda para baixar o estoque						
				int quantidadeAtual = produto.getQuantidade() - itemVenda.getQuantidade();
				if (quantidadeAtual >= 0) { // se a quantidadeAtual for maior >= 0 ele consegue vender, se for abaixo disso ele propaga o erro para o VendaBean
					produto.setQuantidade(new Short(quantidadeAtual + "")); 
					// setar a nova quantidade subtraindo a quantidade em estoque - a quantidade vendida (utilizar conversão para String devido a variavel estar em Short)
					sessao.update(produto); // atualizar o produto com a nova quantidade
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}				
			}

			transacao.commit(); //confirmar a transação

		} catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}	
}