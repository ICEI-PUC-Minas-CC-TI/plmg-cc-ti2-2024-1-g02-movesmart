package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO {

    // Construtor
    public UsuarioDAO() {
        super();
        conectar();
    }

    // Insere um usuário
    public boolean insert(Usuario usuario) {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("INSERT INTO usuario (nome, email, login, senha) VALUES (?, ?, ?, ?)")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getLogin());
            st.setString(4, usuario.getSenha());
            st.executeUpdate();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    // Atualiza um usuário
    public boolean update(Usuario usuario) {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id_usuario = ?")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.setInt(4, usuario.getIdUsuario());
            st.executeUpdate();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    // Deleta um usuário
    public boolean delete(int id) {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    // Busca um usuário pelo id
    public Usuario getById(int idUsuario) {
        Usuario usuario = null;
        try (PreparedStatement st = conexao.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?")) {
            st.setInt(1, idUsuario);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    // Busca todos os usuários
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement st = conexao.prepareStatement("SELECT * FROM usuario")) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    // Verifica se um usuário existe
    public boolean exists(int id) {
        boolean exists = false;
        try (PreparedStatement st = conexao.prepareStatement("SELECT 1 FROM usuario WHERE id_usuario = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return exists;
    }

}
