package service;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private DAO dao = new DAO();
    Gson gson = new Gson();

    // Insere um usuário
    public Object insert(Request request, Response response) {

        //verifica se o corpo da requisição é nulo
        if(request.body() == null){
            response.status(400); // 400 Bad Request
            return "Corpo da requisição nulo!";
        }

        //transforma o json em um objeto do tipo Usuario
        Usuario usuario = gson.fromJson(request.body(), Usuario.class);

        if (usuarioDAO.exists(usuario.getIdUsuario())) {
            response.status(409); // 409 Conflict
            return "Usuário já existe!";
        }

        //verifica se a senha é valida
        try {
            String senhaMD5 = dao.toMD5Password(usuario.getSenha());
            usuario.setSenha(senhaMD5);
        } catch (Exception e) {
            response.status(500); // 500 Internal Server Error
            return "Erro ao converter a senha para MD5: " + e.getMessage();
        }


        //verifica se o usuário foi inserido com sucesso
        if (usuarioDAO.insert(usuario)) {
            response.status(201); // 201 Created
            return "Usuario inserido com sucesso!";
        } else {
            response.status(500); // 500 Internal Server Error
            return "Erro ao inserir contato!";
        }
    }

    // Atualiza um usuário
    public Object update(Request request, Response response) {

        //verifica se o corpo da requisição é nulo
        if (request.body() == null) {
            response.status(400); // 400 Bad Request
            return "Corpo da requisição nulo!";
        }

        //transforma o json em um objeto do tipo Usuario
        Usuario usuario = gson.fromJson(request.body(), Usuario.class);

        //pega o id do usuario
        int id = Integer.parseInt(request.params(":id"));

        //seta o id do usuario
        usuario.setIdUsuario(id);

        if (!usuarioDAO.exists(usuario.getIdUsuario())) {
            response.status(404); // 404 Not Found
            return "Usuário não encontrado!";
        }

        //verifica se o id é valido
        if (usuario.getIdUsuario() <= 0) {
            response.status(400); // 400 Bad Request
            return "ID de usuário inválido!";
        }


        //verifica se a senha é valida
        try {
            if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
                String senhaMD5 = dao.toMD5Password(usuario.getSenha());
                usuario.setSenha(senhaMD5);
            }

            //verifica se o usuario foi atualizado com sucesso
            if (usuarioDAO.update(usuario)) {
                response.status(200); // 200 OK
                return "Usuário atualizado com sucesso!";
            } else {
                response.status(404); // 404 Not Found
                return "Usuário não encontrado!";
            }
        } catch (Exception e) {
            response.status(500); // 500 Internal Server Error
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    // Deleta um usuário
    public Object delete(Request request, Response response) {
        try {
            // Obter o ID do parâmetro de caminho
            int idUsuario = Integer.parseInt(request.params(":id"));

            // Verifica se o id é valido
            if (idUsuario <= 0) {
                response.status(400); // 400 Bad Request
                return "ID de usuário inválido!";
            }

            // Verifica se o usuário existe
            if (!usuarioDAO.exists(idUsuario)) {
                response.status(404); // 404 Not Found
                return "Usuário não encontrado!";
            }

            // Verifica se o usuário foi excluído com sucesso
            if (usuarioDAO.delete(idUsuario)) {
                response.status(200); // 200 OK
                return "Usuário excluído com sucesso!";
            } else {
                response.status(500); // 500 Internal Server Error
                return "Erro ao excluir usuário!";
            }
        } catch (Exception e) {
            response.status(500); // 500 Internal Server Error
            return "Erro ao excluir usuário: " + e.getMessage();
        }
    }

    // Obtem um usuário pelo ID
    public Object getById(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = usuarioDAO.getById(id);
        if (usuario != null) {
            return new Gson().toJson(usuario);
        } else {
            response.status(404); // 404 Not Found
            return "Usuário não encontrado!";
        }
    }

    // Obtem todos os usuários
    public Object getAll(Request request, Response response) {
        List<Usuario> usuarios = usuarioDAO.getAll();
        if (usuarios != null) {
            return new Gson().toJson(usuarios);
        } else {
            response.status(404); // 404 Not Found
            return "Nenhum usuário encontrado!";
        }
    }

    public Object authenticate(Request request, Response response) {
        // Verifica se o corpo da requisição é nulo
        if (request.body() == null) {
            response.status(400); // 400 Bad Request
            return "Corpo da requisição nulo!";
        }

        // Transforma o json em um objeto do tipo Usuario
        Usuario usuario = gson.fromJson(request.body(), Usuario.class);

        // Pega o nome de usuário e a senha
        String login = usuario.getLogin();
        String senha = usuario.getSenha();

        // Autentica o usuário
        Usuario usuarioAutenticado = usuarioDAO.getByUsername(login);
        if (usuarioAutenticado == null) {
            response.status(401); // 401 Unauthorized
            return "Falha na autenticação!";
        }

        String senhaMD5;
        try {
            senhaMD5 = dao.toMD5Password(senha);
        } catch (Exception e) {
            response.status(500); // 500 Internal Server Error
            return "Erro ao converter a senha para MD5: " + e.getMessage();
        }

        if (usuarioAutenticado.getSenha().equals(senhaMD5)) {
            response.status(200); // 200 OK
            return "Usuário autenticado com sucesso!";
        } else {
            response.status(401); // 401 Unauthorized
            return "Falha na autenticação!";
        }
    }
}

