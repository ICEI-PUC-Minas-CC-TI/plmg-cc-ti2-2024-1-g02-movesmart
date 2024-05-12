package dao;

import model.Od;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdDAO extends DAO 
{
    public OdDAO ( ) 
    {
        super( );
        conectar( );
    } // end OdDAO ( )

    public boolean insert ( Od od ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "INSERT INTO od (linha, horario, origem, destino) VALUES (?, ?, ?)" ) ) 
        {
            st.setString( 0, od.getLinha  ( ) );
            st.setString( 1, od.getHorario( ) );
            st.setString( 2, od.getOrigem ( ) );
            st.setString( 3, od.getDestino( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    public boolean update ( Od od ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "UPDATE od SET linha = ?, horario = ?, origem = ?, destino = ? WHERE id_od = ?" ) ) 
        {
            st.setString( 0, od.getLinha  ( ) );
            st.setString( 1, od.getHorario( ) );
            st.setString( 2, od.getOrigem ( ) );
            st.setString( 3, od.getDestino( ) );
            st.setInt   ( 4, od.getIdOd   ( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    public boolean delete ( int idOd ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "DELETE FROM od WHERE id_od = ?" ) ) 
        {
            st.setInt( 1, idOd );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    public Od getById ( int idOd ) 
    {
        Od od = null;
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM od WHERE id_od = ?" ) ) 
        {
            st.setInt( 1, idOd );
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                if( rs.next( ) ) 
                {
                    od = new Od( );
                    od.setIdOd   ( rs.getInt   ( "id_od"   ) );
                    od.setLinha  ( rs.getString( "linha"   ) );
                    od.setHorario( rs.getString( "horario" ) );
                    od.setOrigem ( rs.getString( "origem"  ) );
                    od.setDestino( rs.getString( "destino" ) );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( od );
    } // end getById ( )

    public List<Od> getAll ( ) 
    {
        List<Od> ods = new ArrayList<>( );
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM od" ) ) 
        {
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                while( rs.next( ) ) 
                {
                    Od od = new Od( );
                    od.setIdOd   ( rs.getInt   ( "id_od"   ) );
                    od.setLinha  ( rs.getString( "linha"   ) );
                    od.setHorario( rs.getString( "horario" ) );
                    od.setOrigem ( rs.getString( "origem"  ) );
                    od.setDestino( rs.getString( "destino" ) );
                    ods.add( od );
                }
            }
        }
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( ods );
    } // end getAll ( )
    
} // end class odDAO
