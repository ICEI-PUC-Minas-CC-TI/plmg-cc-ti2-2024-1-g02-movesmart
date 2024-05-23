/* 
 * 
 *  EM TESTE
 * 
 */

package app;

import static spark.Spark.*;
import service.OdService;

public class AplicacaoOd {
    
    private static OdService odService = new OdService();

    static final String LOCAL_HOST = "http://127.0.0.1:5500";

    static final int PORT = 8080;

    public static void main(String[] args) {

        // Configuração da porta
        port(getPort());

        // Configuração do CORS
        configureCors();

        // Configuração da rota
        configureODRoute();

    }

    // Retorna a porta do servidor
    private static int getPort() {
        return PORT;
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

    // Configuração da rota
    private static void configureODRoute() {
        post("/od/insert", (request, response) -> odService.insert(request, response));

        get("/od/:id", (request, response) -> odService.get(request, response));

        get("/od/list/:orderby", (request, response) -> odService.getAll(request, response));

        get("/od/update/:id", (request, response) -> odService.getToUpdate(request, response));

        post("/od/update/:id", (request, response) -> odService.update(request, response));

        get("/od/delete/:id", (request, response) -> odService.delete(request, response));
    }
}
