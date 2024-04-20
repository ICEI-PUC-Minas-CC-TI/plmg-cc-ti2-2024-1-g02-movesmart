package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try (PreparedStatement st = conexao.prepareStatement("UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.setInt(4, usuario.getId());
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
        try (PreparedStatement st = conexao.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean exists(int id) {
        boolean exists = false;
        try (PreparedStatement st = conexao.prepareStatement("SELECT 1 FROM usuario WHERE id = ?")) {
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
