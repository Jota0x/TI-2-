package app;

import service.PessoaService;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Projeto {

    // Instanciamos o serviço que fará a ponte entre a Web e o Banco
    private static PessoaService pessoaService = new PessoaService();

    public static void main(String[] args) {
        // Configuração da porta
        port(4567);

        staticFiles.location("/public");

        // MAPEAMENTO DAS ROTAS

        // Rota para INSERIR
        post("/usuario/inserir", (request, response) -> pessoaService.add(request, response));

        // Rota para LISTAR
        get("/usuario/listar", (request, response) -> pessoaService.getAll(request, response));

        // Rota para EXCLUIR
        get("/usuario/excluir/:id", (request, response) -> pessoaService.remove(request, response));

        // Rota para ATUALIZAR
        post("/usuario/atualizar", (request, response) -> pessoaService.update(request, response));

        // Rota para buscar um específico
        get("/usuario/:id", (request, response) -> pessoaService.get(request, response));

        System.out.println("Servidor Spark rodando em http://localhost:4567");
        System.out.println("Aguardando conexões via navegador...");
    }
}