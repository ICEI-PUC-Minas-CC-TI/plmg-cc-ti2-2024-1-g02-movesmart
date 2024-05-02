package model;

import java.util.Date;

public class Horario 
{
    private int    idHorario;
    private Date   horario;

    public Horario ( ) 
    {
        this.horario = new Date( );
    }

    public Horario ( int idHorario, Date horario ) 
    {
        setIdHorario ( idHorario );
        setHorario   ( horario   );
    }

    public void setIdHorario ( int    idHorario ) { this.idHorario = idHorario; }
    public void setHorario   ( Date   horario   ) { this.horario   = horario  ; }

    public int    getIdHorario ( ) { return ( idHorario ); }
    public Date   getHorario   ( ) { return ( horario   ); }

    @Override
    public String toString() {
        return ( "Horario [id=" + idHorario + ", horario=" + horario + "]" );
    }

    @Override
    public boolean equals( Object obj ) {
        return ( this.getIdHorario( ) == ((Horario)obj).getIdHorario( ) );
    }
} // end class
