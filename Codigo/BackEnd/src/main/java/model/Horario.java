package model;

import java.util.Date;

public class Horario 
{
    private int    idHorario;
    private Date   horario;
    private Onibus onibus;

    public Horario ( ) 
    {
        this.horario = new Date();
        this.onibus = new Onibus();
    }

    public Horario ( int idHorario, Date horario, Onibus onibus ) 
    {
        setIdHorario ( idHorario );
        setHorario   ( horario   );
        setOnibus    ( onibus    );
    }

    public void setIdHorario ( int    idHorario ) { this.idHorario = idHorario; }
    public void setHorario   ( Date   horario   ) { this.horario   = horario  ; }
    public void setOnibus    ( Onibus onibus    ) { this.onibus    = onibus   ; }

    public int    getIdHorario ( ) { return ( idHorario ); }
    public Date   getHorario   ( ) { return ( horario   ); }
    public Onibus getOnibus    ( ) { return ( onibus    ); }

    @Override
    public String toString() {
        return ( "Horario [id=" + idHorario + ", horario=" + horario + ", onibus=" + onibus + "]" );
    }

    @Override
    public boolean equals( Object obj ) {
        return ( this.getIdHorario( ) == ((Horario)obj).getIdHorario( ) );
    }
} // end class
