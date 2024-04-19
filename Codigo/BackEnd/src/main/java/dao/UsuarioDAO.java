package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO extends DAO {

    public UsuarioDAO() {
        super();
        conectar();
    }

    public boolean insert(Usuario usuario) {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.executeUpdate();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
