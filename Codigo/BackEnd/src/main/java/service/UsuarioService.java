package service;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private DAO dao = new DAO();
    Gson gson = new Gson();

    public Object insert(Request request, Response response) {

        if(request.body() == null){
            response.status(500); // 500 Internal Server Error
            return "Corpo da requisição nulo!";
        }

        Usuario usuario = gson.fromJson(request.body(), Usuario.class);

        try {
            String senhaMD5 = dao.toMD5Password(usuario.getSenha());
            usuario.setSenha(senhaMD5);
        } catch (Exception e) {
            response.status(500); // 500 Internal Server Error
            return "Erro ao converter a senha para MD5: " + e.getMessage();
        }

        if (usuarioDAO.insert(usuario)) {
            response.status(201); // 201 Created
            return "Usuario inserido com sucesso!";
        } else {
            response.status(500); // 500 Internal Server Error
            return "Erro ao inserir contato!";
        }
    }

//    public Object getById(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        Contato contato = contatoDAO.getById(id);
//        if (contato != null) {
//            return contato;
//        } else {
//            response.status(404); // 404 Not Found
//            return "Contato não encontrado!";
//        }
//    }
//
//    public Object getToUpdate(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        Contato contato = contatoDAO.getById(id);
//
//        if (contato != null) {
//            response.status(200); // 200 OK
//            makeForm(FORM_UPDATE, contato, FORM_ORDERBY_ID);
//        } else {
//            response.status(404); // 404 Not Found
//            String resp = "Contato " + id + " não encontrado.";
//            makeForm();
//            form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
//        }
//
//        return form;
//    }
//
//    public Object getAll(Request request, Response response) {
//        int orderBy = Integer.parseInt(request.params(":orderby"));
//        makeForm(orderBy);
//        response.header("Content-Type", "text/html");
//        response.header("Content-Encoding", "UTF-8");
//        return form;
//    }
//
//    public Object update(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        Contato contato = contatoDAO.getById(id);
//        String resp = "";
//
//        if (contato != null) {
//            contato.setNome(request.queryParams("nome"));
//            contato.setEmail(request.queryParams("email"));
//            contato.setTelefone(request.queryParams("telefone"));
//            contatoDAO.update(contato);
//            response.status(200); // 200 OK
//            resp = "Contato (ID " + contato.getId() + ") atualizado!";
//        } else {
//            response.status(404); // 404 Not Found
//            resp = "Contato (ID " + id + ") não encontrado!";
//        }
//        makeForm();
//        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
//    }
//
//    public Object delete(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        Contato contato = contatoDAO.getById(id);
//        String resp = "";
//
//        if (contato != null) {
//            contatoDAO.delete(id);
//            response.status(200); // 200 OK
//            resp = "Contato (ID " + id + ") excluído!";
//        } else {
//            response.status(404); // 404 Not Found
//            resp = "Contato (ID " + id + ") não encontrado!";
//        }
//        makeForm();
//        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
//    }
}

