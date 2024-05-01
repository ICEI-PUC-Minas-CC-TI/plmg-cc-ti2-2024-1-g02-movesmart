package model;

public class Od {
    private int    idOd   ;
    private double horario;
    private String origem ;
    private String destino;

    public Od ( )
    {
        this.horario = 0.0;
        this.origem  = "";
        this.destino = "";
    }

    public Od ( double horario, String origem, String destino ){
        setHorario ( horario );
        setOrigem  ( origem );
        setDestino ( destino );
    }

    public int    getIdOd    ( ) { return ( this.idOd    ); }
    public double getHorario ( ) { return ( this.horario ); }
    public String getOriem   ( ) { return ( this.origem  ); }
    public String getDestino ( ) { return ( this.destino ); }

    public void   setIdOd    ( int    idOd    ) { this.idOd    = idOd   ; }
    public void   setHorario ( double horario ) { this.horario = horario; }
    public void   setOrigem  ( String origem  ) { this.origem  = origem ; }
    public void   setDestino ( String destino ) { this.destino = destino; }

    @Override
    public String toString ( ) {
        return ( "OD [id=" + idOd + ", origem=" + origem + ", destino=" + destino + ", horario=" + horario +"]" );
    }

    @Override
    public boolean equals( Object obj ) {
        return ( this.getIdOd( ) == ((Od)obj).getIdOd( ) );
    }
} // end class 
