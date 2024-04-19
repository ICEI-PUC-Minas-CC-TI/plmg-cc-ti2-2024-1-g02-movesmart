package app;

import dao.DAO;
import service.UsuarioService;

import static spark.Spark.*;

public class Aplicacao {
    private static UsuarioService usuarioService = new UsuarioService();
    private static DAO dao = new DAO();

    public static void main(String[] args) {
        port(getPort());

        staticFiles.location("/public");

        post("/usuario", (request, response) -> {
            try {
                return usuarioService.insert(request, response);
            } catch (Exception e) {
                response.status(500);
                return "Erro ao inserir usuário: " + e.getMessage();
            }
        });
    }

    private static int getPort() {
        return 6796;
    }
}
