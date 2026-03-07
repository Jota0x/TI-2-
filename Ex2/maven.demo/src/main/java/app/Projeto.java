package app;

import java.util.List;

import dao.DAO;
import dao.pessoaDAO;
import model.Pessoa;

//classe responsavel pela aplicação do projeto
public class Projeto {
	
	public static void main(String[] args) throws Exception {
		
		pessoaDAO PESSOADAO = new pessoaDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Pessoa pessoa = new Pessoa(12, "pablo", "pablo",'M');
		if(PESSOADAO.insert(pessoa) == true) {
			System.out.println("Inserção com sucesso -> " + pessoa.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + pessoa.getLogin() + "): " + PESSOADAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Pessoa> pessoas = PESSOADAO.getSexoMasculino();
		for (Pessoa u: pessoas) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar senha (código (" + pessoa.getCodigo() + ") === ");
		pessoa.setSenha(DAO.toMD5("pablo"));
		PESSOADAO.update(pessoa);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + pessoa.getLogin() + "): " + PESSOADAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + pessoa.getLogin() + "): " + PESSOADAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		pessoas = PESSOADAO.getOrderByCodigo();
		for (Pessoa u: pessoas) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + pessoa.getCodigo() + ") === ");
		PESSOADAO.delete(pessoa.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		pessoas = PESSOADAO.getOrderByLogin();
		for (Pessoa u: pessoas) {
			System.out.println(u.toString());
		}
	}
}
