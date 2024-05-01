package model;

import java.util.ArrayList;
import java.util.List;

public class Rota 
{
    private int         idRota;
    private List<Ponto> pontos;

    public Rota ( ) 
    {
        this.pontos = new ArrayList<>();
    }

    public Rota ( int idRota, String nome ) 
    {
        setIdRota( idRota );
        this.pontos = new ArrayList<>();
    }

    public void setIdRota ( int idRota  ) { this.idRota = idRota       ; }
    public void addPonto  ( Ponto ponto ) { this.pontos.add( ponto )   ; }
    public void delPonto  ( Ponto ponto ) { this.pontos.remove( ponto ); }

    public int         getIdRota ( ) { return ( this.idRota ); }
    public List<Ponto> getPontos ( ) { return ( this.pontos ); }

    @Override
    public String toString( ) {
        return ( "Rota [id=" + idRota + ", pontos=" + pontos + "]" );
    }

    @Override
    public boolean equals( Object obj ) 
    {
        return ( this.getIdRota( ) == ((Rota)obj).getIdRota( ));
    }
} // end class
