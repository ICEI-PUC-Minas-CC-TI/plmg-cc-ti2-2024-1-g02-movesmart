package app;

import service.UsuarioService;

import static spark.Spark.*;

public class Aplicacao {
    private static UsuarioService usuarioService = new UsuarioService();

    public static void main(String[] args) {

        // Configuração da porta
        port(getPort());

        // Configuração do diretório público
        staticFiles.location("/public");

        // Filtro para todas as requisições
        post("/usuario", (request, response) -> {
            try {
                return usuarioService.insert(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao inserir usuário: " + e.getMessage();
            }
        });
        put("/usuario/:id", (request, response) -> {
            try {
                return usuarioService.update(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao atualizar usuário: " + e.getMessage();
            }
        });
        delete("/usuario/:id", (request, response) -> {
            try {
                return usuarioService.delete(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao excluir usuário: " + e.getMessage();
            }
        });
        get("/usuario/:id", (request, response) -> {
            try {
                return usuarioService.getById(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao buscar usuário: " + e.getMessage();
            }
        });
    }

    // Retorna a porta do servidor
    private static int getPort() {
        return 6796;
    }
}
