package model;

public class Ponto 
{
    private int    idPonto;
    private String logradouro;
    private int    numero;

    public Ponto ( )
    {
        this.logradouro = "";
        this.numero     = 0 ;
    }

    public Ponto ( String logradouro, int numero )
    {
        setLogradouro ( logradouro );
        setNumero     ( numero     );
    }

    public void setIdPonto    ( int    idPonto    ) { this.idPonto    = idPonto   ; }
    public void setLogradouro ( String logradouro ) { this.logradouro = logradouro; }
    public void setNumero     ( int    numero     ) { this.numero     = numero    ; }

    public int    getIdPonto    (  ) { return ( this.idPonto    ); }
    public String getLogradouro (  ) { return ( this.logradouro ); }
    public int    getNumero     (  ) { return ( this.numero     ); }

    @Override
    public String toString ( ) {
        return ( "Ponto [id=" + idPonto + ", logradouro=" + logradouro + ", numero=" + numero + "]" );
    }

    @Override
    public boolean equals( Object obj ) {
        return ( this.getIdPonto( ) == ((Ponto)obj).getIdPonto( ) );
    }

} // end class 
