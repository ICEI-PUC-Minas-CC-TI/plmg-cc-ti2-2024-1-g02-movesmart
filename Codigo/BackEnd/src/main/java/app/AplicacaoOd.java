/* 
 * 
 *  EM TESTE
 * 
 */

/* package app;

import static spark.Spark.*;
import service.OdService;

public class AplicacaoOd {
    
    private static OdService odService = new OdService();
    
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/od/insert", (request, response) -> odService.insert(request, response));

        get("/od/:id", (request, response) -> odService.get(request, response));
        
        get("/od/list/:orderby", (request, response) -> odService.getAll(request, response));

        get("/od/update/:id", (request, response) -> odService.getToUpdate(request, response));
        
        post("/od/update/:id", (request, response) -> odService.update(request, response));

        get("/od/delete/:id", (request, response) -> odService.delete(request, response));  
    }
}
 */