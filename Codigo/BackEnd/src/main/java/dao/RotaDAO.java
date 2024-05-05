package dao;

import model.Ponto;
import model.Rota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO extends DAO 
{
    public RotaDAO( ) 
    {
        super( );
        conectar( );
    } // end RotaDAO ( )

    public boolean insert ( Rota rota ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "INSERT INTO Rota (id_rota) VALUES (?)" ) ) 
        {
            st.setInt(1, rota.getIdRota());
            st.executeUpdate();
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    public boolean update ( Rota rota ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "UPDATE Rota SET id_rota = ? WHERE id_rota = ?" ) ) 
        {
            st.setInt( 1, rota.getIdRota( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    public boolean delete ( int idRota ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "DELETE FROM Rota WHERE id_rota = ?" ) ) 
        {
            st.setInt( 1, idRota );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    public Rota getById ( int idRota ) 
    {
        Rota rota = null;
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Rota WHERE id_rota = ?" ) ) 
        {
            st.setInt( 1, idRota );
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                if( rs.next( ) ) 
                {
                    rota = new Rota( );
                    rota.setIdRota( rs.getInt( "id_rota" ) );
                    // Recupera os pontos da rota
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( rota );
    } // end getById ( )

    public List<Rota> getAll ( ) 
    {
        List<Rota> rotas = new ArrayList<>( );
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Rota" ) ) 
        {
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                while( rs.next( ) ) 
                {
                    Rota rota = new Rota( );
                    rota.setIdRota( rs.getInt( "id_rota" ) );
                    // Recupera os pontos da rota
                    rotas.add( rota );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( rotas );
    } // end getAll ( )

} // end class RotaDAO
