package br.com.lucas.pharma.dao;

import java.text.ParseException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Pessoa;
import br.com.lucas.pharma.domain.Usuario;
import br.com.lucas.pharma.enumeracao.TipoUsuario;

public class UsuarioDAOTest {

	@Test	
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(7L);		
		
		Usuario usuario = new Usuario();		
		
		usuario.setSenhaSemCriptografia("123456");		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia()); // criptografar senha utiliza-se SimpleHash
		usuario.setSenha(hash.toHex()); // criptografar a senha e armazenar em md5		
		usuario.setTipoUsuario(TipoUsuario.BALCONISTA); // A de Admin
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test	
	@Ignore
	public void autenticar() {
		String cpf = "999.999.999-99";
		String senha = "nerdss233204";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuario autenticado: " + usuario);
	}
}