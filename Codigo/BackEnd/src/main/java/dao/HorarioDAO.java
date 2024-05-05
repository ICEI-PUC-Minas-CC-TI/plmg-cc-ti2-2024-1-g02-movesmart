package dao;

import model.Horario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HorarioDAO extends DAO 
{
    public HorarioDAO ( ) 
    {
        super( );
        conectar( );
    } // end HorarioDAO ( )

    public boolean insert ( Horario horario ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "INSERT INTO Horario (horario) VALUES (?)" ) ) 
        {
            st.setObject( 1, horario.getHorario( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end insert ( )

    public boolean update ( Horario horario ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "UPDATE Horario SET horario = ? WHERE id_horario = ?" ) ) 
        {
            st.setObject( 1, horario.getHorario  ( ) );
            st.setInt   ( 2, horario.getIdHorario( ) );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            throw new RuntimeException( e );
        }
        return ( status );
    } // end update ( )

    public boolean delete ( int idHorario ) 
    {
        boolean status = false;
        try( PreparedStatement st = conexao.prepareStatement( "DELETE FROM Horario WHERE id_horario = ?" ) ) 
        {
            st.setInt( 1, idHorario );
            st.executeUpdate( );
            status = true;
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( status );
    } // end delete ( )

    public Horario getById ( int idHorario ) 
    {
        Horario horario = null;
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Horario WHERE id_horario = ?" ) ) 
        {
            st.setInt( 1, idHorario );
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                if( rs.next( ) ) 
                {
                    horario = new Horario( );
                    horario.setIdHorario( rs.getInt   ( "id_horario" ) );
                    horario.setHorario  ( rs.getObject( "horario", java.sql.Time.class ) );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( horario );
    } // end getById ( )

    public List<Horario> getAll ( ) 
    {
        List<Horario> horarios = new ArrayList<>( );
        try( PreparedStatement st = conexao.prepareStatement( "SELECT * FROM Horario" ) ) 
        {
            try( ResultSet rs = st.executeQuery( ) ) 
            {
                while( rs.next( ) ) 
                {
                    Horario horario = new Horario( );
                    horario.setIdHorario( rs.getInt   ( "id_horario" ) );
                    horario.setHorario  ( rs.getObject( "horario", java.sql.Time.class ) );
                    horarios.add( horario );
                }
            }
        } 
        catch( SQLException e ) {
            System.err.println( e.getMessage( ) );
        }
        return ( horarios );
    } // end getAll ( )

} // end class HorarioDAO
