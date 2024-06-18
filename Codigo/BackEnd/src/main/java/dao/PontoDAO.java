package dao;

import model.Ponto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PontoDAO extends DAO 
{

    public PontoDAO ( ) 
    {
        super( );
        conectar( );
    } // end PontoDAO ( )

    public boolean insert ( Ponto ponto ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "INSERT INTO ponto (logradouro, numero) VALUES (?, ?)" ) ) 
        {
            st.setString( 1, ponto.getLogradouro( ) );
            st.setInt   ( 2, ponto.getNumero    ( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    public boolean update ( Ponto ponto ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "UPDATE ponto SET logradouro = ?, numero = ? WHERE id_ponto = ?" ) ) 
        {
            st.setString( 1, ponto.getLogradouro( ) );
            st.setInt   ( 2, ponto.getNumero    ( ) );
            st.setInt   ( 3, ponto.getIdPonto   ( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    public boolean delete ( int idPonto ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "DELETE FROM ponto WHERE id_ponto = ?" ) ) 
        {
            st.setInt( 1, idPonto );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    public Ponto getById ( int idPonto ) 
    {
        Ponto ponto = null;
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM ponto WHERE id_ponto = ?" ) ) 
        {
            st.setInt( 1, idPonto );
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                if( rs.next( ) ) 
                {
                    ponto = new Ponto( );
                    ponto.setIdPonto   ( rs.getInt   ( "id_ponto"   ) );
                    ponto.setLogradouro( rs.getString( "logradouro" ) );
                    ponto.setNumero    ( rs.getInt   ( "numero"     ) );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( ponto );
    } // end getById ( )

    public List<Ponto> getAll ( ) 
    {
        List<Ponto> pontos = new ArrayList<>( );
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM ponto" ) ) 
        {
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                while( rs.next( ) ) 
                {
                    Ponto ponto = new Ponto( );
                    ponto.setIdPonto   ( rs.getInt   ( "id_ponto"   ) );
                    ponto.setLogradouro( rs.getString( "logradouro" ) );
                    ponto.setNumero    ( rs.getInt   ( "numero"     ) );
                    pontos.add( ponto );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( pontos );
    } // end getAll ( )

} // end class PontoDAO
