package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO 
{
    // Construtor
    public UsuarioDAO ( ) 
    {
        super( );
        conectar( );
    } // end UsuarioDAO ( )

    // Insere um usuário
    public boolean insert ( Usuario usuario ) 
    {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("INSERT INTO usuarios (nome, email, telefone, login, senha) VALUES (?, ?, ?, ?, ?)")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getTelefone());
            st.setString(4, usuario.getLogin());
            st.setString(5, usuario.getSenha());
            st.executeUpdate();
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    // Atualiza um usuário
    public boolean update ( Usuario usuario ) 
    {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("UPDATE usuarios SET nome = ?, email = ?, login = ?, senha = ?, telefone = ? WHERE id_usuario = ?")) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getLogin());
            st.setString(4, usuario.getSenha());
            st.setString(5, usuario.getTelefone());
            st.setInt(6, usuario.getIdUsuario());
            st.executeUpdate();
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    // Deleta um usuário
    public boolean delete ( int id ) 
    {
        boolean status = false;
        try (PreparedStatement st = conexao.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    // Busca um usuário pelo id
    public Usuario getById ( int idUsuario ) 
    {
        Usuario usuario = null;
        try (PreparedStatement st = conexao.prepareStatement("SELECT * FROM usuarios WHERE id_usuario = ?")) {
            st.setInt(1, idUsuario);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( usuario );
    } // end getById ( )

    // Busca todos os usuários
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement st = conexao.prepareStatement("SELECT * FROM usuarios")) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuarios.add(usuario);
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( usuarios );
    } // end getAll ( )

    // Busca um usuário pelo login
    public Usuario getByUsername(String username) {
        Usuario usuario = null;
        try (PreparedStatement st = conexao.prepareStatement("SELECT * FROM usuarios WHERE login = ?")) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }


    // Verifica se um usuário existe
    public boolean exists ( int id ) 
    {
        boolean exists = false;
        try (PreparedStatement st = conexao.prepareStatement("SELECT 1 FROM usuarios WHERE id_usuario = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( exists );
    } // end exists ( )

} // end class UsuarioDAO
