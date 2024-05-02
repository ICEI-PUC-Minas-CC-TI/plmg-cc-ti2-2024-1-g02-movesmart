package dao;

import model.Onibus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnibusDAO extends DAO 
{
    public OnibusDAO ( ) 
    {
        super( );
        conectar( );
    }

    public boolean insert ( Onibus onibus ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "INSERT INTO Onibus (numero) VALUES (?)" ) ) 
        {
            st.setString( 1, onibus.getNumero( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    public boolean update ( Onibus onibus ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "UPDATE Onibus SET numero = ? WHERE id_onibus = ?" ) )
        {
            st.setString( 1, onibus.getNumero  ( ) );
            st.setInt   ( 2, onibus.getIdOnibus( ) );
            st.executeUpdate();
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    public boolean delete ( int idOnibus )
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "DELETE FROM Onibus WHERE id_onibus = ?" ) ) 
        {
            st.setInt( 1, idOnibus );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    public Onibus getById ( int idOnibus ) 
    {
        Onibus onibus = null;
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Onibus WHERE id_onibus = ?" ) ) 
        {
            st.setInt( 1, idOnibus );
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                if( rs.next( ) ) 
                {
                    onibus = new Onibus( );
                    onibus.setIdOnibus( rs.getInt   ( "id_onibus" ) );
                    onibus.setNumero  ( rs.getString( "numero"    ) );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( onibus );
    } // end getById ( )

    public List<Onibus> getAll ( ) 
    {
        List<Onibus> onibusList = new ArrayList<>( );
        try(PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Onibus" ) ) 
        {
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                while( rs.next( ) ) 
                {
                    Onibus onibus = new Onibus( );
                    onibus.setIdOnibus( rs.getInt   ( "id_onibus" ) );
                    onibus.setNumero  ( rs.getString( "numero" ) );
                    onibusList.add( onibus );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( onibusList );
    } // end getAll ( )
} // end class OnibusDAO
