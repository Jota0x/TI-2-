package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.pessoaDAO;
import model.Pessoa;

public class Projeto {
    
    public static void main(String[] args) throws Exception {
        Scanner dado = new Scanner(System.in);
        pessoaDAO PESSOADAO = new pessoaDAO();
        int opcao;

        do {
            System.out.println("\n----------------------------");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Excluir");
            System.out.println("4) Atualizar");
            System.out.println("5) Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = dado.nextInt();
            dado.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("\n==== LISTA DE USUÁRIOS === ");
                    List<Pessoa> lista = PESSOADAO.getOrderByCodigo();
                    for (Pessoa u : lista) {
                        System.out.println(u.toString());
                    }
                    break;

                case 2:
                    System.out.println("\n==== INSERIR NOVO USUÁRIO === ");
                    System.out.print("Código: ");
                    int cod = dado.nextInt();
                    dado.nextLine(); // Limpar buffer
                    System.out.print("Login: ");
                    String login = dado.nextLine();
                    System.out.print("Senha: ");
                    String senha = dado.nextLine();
                    System.out.print("Sexo (M/F): ");
                    char sexo = dado.next().toUpperCase().charAt(0);

                    Pessoa novaPessoa = new Pessoa(cod, login, senha, sexo);
                    if (PESSOADAO.insert(novaPessoa)) {
                        System.out.println("Sucesso: Usuário inserido!");
                    }
                    break;

                case 3:
                    System.out.println("\n==== EXCLUIR USUÁRIO === ");
                    System.out.print("Digite o código para excluir: ");
                    int idExcluir = dado.nextInt();
                    if (PESSOADAO.delete(idExcluir)) {
                        System.out.println("Sucesso: Usuário removido.");
                    } else {
                        System.out.println("Erro: Não foi possível excluir.");
                    }
                    break;

                case 4:
                    System.out.println("\n==== ATUALIZAR DADOS (SENHA) === ");
                    System.out.print("Digite o código do usuário: ");
                    int idAtu = dado.nextInt();
                    dado.nextLine(); // Limpar buffer
                    System.out.print("Nova Senha: ");
                    String novaSenha = dado.nextLine();
                    
                    Pessoa pAtu = new Pessoa();
                    pAtu.setCodigo(idAtu);
                    pAtu.setSenha(DAO.toMD5(novaSenha));
                    
                    if (PESSOADAO.update(pAtu)) {
                        System.out.println("Sucesso: Senha atualizada!");
                    } else {
                        System.out.println("Erro: Falha na atualização.");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 5);

        dado.close();
    }
}