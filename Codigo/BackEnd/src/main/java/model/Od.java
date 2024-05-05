package model;

public class Od 
{
    private int    idOd   ;
    private String horario;
    private String origem ;
    private String destino;

    public Od ( )
    {
        this.horario = "";
        this.origem  = "";
        this.destino = "";
    }

    public Od ( int idOd,String horario, String origem, String destino )
    {
        setIdOd    ( idOd    );
        setHorario ( horario );
        setOrigem  ( origem  );
        setDestino ( destino );
    }

    public Od ( String horario, String origem, String destino )
    {
        setHorario ( horario );
        setOrigem  ( origem );
        setDestino ( destino );
    }

    public void   setIdOd    ( int    idOd    ) { this.idOd    = idOd   ; }
    public void   setHorario ( String horario ) { this.horario = horario; }
    public void   setOrigem  ( String origem  ) { this.origem  = origem ; }
    public void   setDestino ( String destino ) { this.destino = destino; }

    public int    getIdOd    ( ) { return ( this.idOd    ); }
    public String getHorario ( ) { return ( this.horario ); }
    public String getOrigem  ( ) { return ( this.origem  ); }
    public String getDestino ( ) { return ( this.destino ); }

    @Override
    public String toString ( ) {
        return ( "OD [id=" + idOd + ", origem=" + origem + ", destino=" + destino + ", horario=" + horario +"]" );
    }

    @Override
    public boolean equals( Object obj ) {
        return ( this.getIdOd( ) == ((Od)obj).getIdOd( ) );
    }
} // end class 
