package service;

import java.util.List;

import dao.pessoaDAO;
import model.Pessoa;
import spark.Request;
import spark.Response;

public class PessoaService {
    private pessoaDAO pDAO = new pessoaDAO();

    // Inserir
    public Object add(Request request, Response response) {
        int codigo = Integer.parseInt(request.queryParams("codigo"));
        String login = request.queryParams("login");
        String senha = request.queryParams("senha");
        char sexo = request.queryParams("sexo").toUpperCase().charAt(0);

        Pessoa pessoa = new Pessoa(codigo, login, senha, sexo);

        if (pDAO.insert(pessoa)) {
            response.status(201); // Criado com sucesso
            return "Usuário " + login + " inserido com sucesso!";
        } else {
            response.status(500); // Erro interno do servidor
            return "Erro ao inserir usuário no banco de dados.";
        }
    }

    // Atualizar
    public Object update(Request request, Response response) {
        int codigo = Integer.parseInt(request.queryParams("codigo"));
        String login = request.queryParams("login");
        String senha = request.queryParams("senha");
        char sexo = request.queryParams("sexo").toUpperCase().charAt(0);

        Pessoa pessoa = new Pessoa(codigo, login, senha, sexo);

        if (pDAO.update(pessoa)) {
            return "Dados do usuário " + codigo + " atualizados com sucesso!";
        } else {
            response.status(404);
            return "Falha ao atualizar: Usuário não encontrado ou erro no banco.";
        }
    }

    // Buscar
    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Pessoa pessoa = pDAO.get(id);

        if (pessoa != null) {
            return "Usuário encontrado: <br>" + pessoa.toString();
        } else {
            response.status(404);
            return "Usuário com o código " + id + " não foi encontrado.";
        }
    }

    // Listar
    public Object getAll(Request request, Response response) {
        List<Pessoa> lista = pDAO.getOrderByCodigo();

        if (lista.isEmpty()) {
            return "Nenhum usuário cadastrado no sistema.";
        } else {
            StringBuilder html = new StringBuilder("<h1>Lista de Usuários</h1>");
            for (Pessoa pessoa : lista) {
                html.append(pessoa.toString()).append("<br>");
            }
            html.append("<br><a href='/index.html'>Voltar</a>");
            return html.toString();
        }
    }

    // Remover
    public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        if (pDAO.delete(id)) {
            return "Usuário " + id + " removido com sucesso!";
        } else {
            response.status(500);
            return "Erro ao tentar remover o usuário " + id;
        }
    }
}