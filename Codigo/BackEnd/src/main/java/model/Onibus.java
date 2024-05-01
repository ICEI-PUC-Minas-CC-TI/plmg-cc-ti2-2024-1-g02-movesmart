package model;

public class Onibus {
    private int idOnibus;
    private int numero;

    public Onibus ( )
    {
        this.numero = 0;
    }

    public Onibus ( int numero )
    {
        setNumero( numero );
    }

    public void setIdOnibus ( int idOnibus ) { this.idOnibus = idOnibus; }
    public void setNumero   ( int numero   ) { this.numero   = numero;   }

    public int  getNumero   ( ) { return ( this.numero   ); }
    public int  getIdOnibus ( ) { return ( this.idOnibus ); }

    @Override
    public String toString ( ) {
        return ( "Onibus [id=" + idOnibus + ", numero=" + numero + "]");
    }

    @Override
    public boolean equals(Object obj) {
        return ( this.getIdOnibus( ) == ((Onibus)obj).getIdOnibus( ) );
    }

} // end class 
