package model;

public class Onibus 
{
    private int idOnibus;
    private String numero;

    public Onibus ( )
    {
        this.numero = "";
    }

    public Onibus ( int idOnibus, String numero )
    {
        setIdOnibus( idOnibus );
        setNumero  ( numero   );
    }

    public Onibus ( String numero )
    {
        setNumero( numero );
    }

    public void setIdOnibus ( int    idOnibus ) { this.idOnibus = idOnibus; }
    public void setNumero   ( String numero   ) { this.numero   = numero;   }

    public int    getIdOnibus ( ) { return ( this.idOnibus ); }
    public String getNumero   ( ) { return ( this.numero   ); }

    @Override
    public String toString ( ) {
        return ( "Onibus [id=" + idOnibus + ", numero=" + numero + "]");
    }

    @Override
    public boolean equals(Object obj) {
        return ( this.getIdOnibus( ) == ((Onibus)obj).getIdOnibus( ) );
    }

} // end class 
