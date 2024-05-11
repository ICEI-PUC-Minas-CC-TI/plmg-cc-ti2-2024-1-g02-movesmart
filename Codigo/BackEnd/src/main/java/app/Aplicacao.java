package app;

import service.UsuarioService;

import static spark.Spark.*;

public class Aplicacao {

    private static UsuarioService usuarioService = new UsuarioService();

    static final String LOCAL_HOST = "http://127.0.0.1:5500";

    public static void main(String[] args) {

        // Configuração da porta
        port(getPort());

        // Configuração do CORS
        configureCors();

        // Configuração dos endpoints para usuário
        configureUserRoute();
    }

    // Retorna a porta do servidor
    public static int getPort() {
        return 6796;
    }

    // Configuração do CORS
    private static void configureCors() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", LOCAL_HOST);
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, PATCH");
            response.header("Access-Control-Allow-Headers", "*");
        });

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
    }

    // Configuração das rotas
    private static void configureUserRoute() {
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

        get("/usuario", (request, response) -> {
            try {
                return usuarioService.getAll(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao buscar usuários: " + e.getMessage();
            }
        });

        post("/login", (request, response) -> {
            try {
                return usuarioService.authenticate(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao autenticar usuário: " + e.getMessage();
            }
        });
    }
}