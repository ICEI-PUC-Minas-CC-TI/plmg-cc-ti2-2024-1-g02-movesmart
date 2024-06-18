package model;

public class Usuario 
{
    // Atributos
    private int    idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String telefone;

    public Usuario( ) 
    {
        this.login    = "";
        this.senha    = "";
        this.email    = "email@default.com"; // Inicializa com um valor padrão
        this.telefone = "";
    }
    // Construtor Alternativo
    public Usuario ( int idUsuario, String nome, String login, String senha, String email, String telefone ) 
    {
        setIdUsuario ( idUsuario );
        setLogin     ( login     );
        setSenha     ( senha     );
        setEmail     ( email     );
        setTelefone  ( telefone  );
    }

    // Construtor Alternativo
    public Usuario ( String nome, String login, String senha, String email, String telefone ) 
    {
        setLogin     ( login     );
        setSenha     ( senha     );
        setEmail     ( email     );
        setTelefone  ( telefone  );
    }

    public void setIdUsuario ( int idUsuario   ) { this.idUsuario = idUsuario ; }
    public void setNome      ( String nome     ) { this.nome      = nome      ; }
    public void setLogin     ( String login    ) { this.login     = login     ; }
    public void setSenha     ( String senha    ) { this.senha     = senha     ; }
    public void setEmail     ( String email    ) { this.email     = email     ; }
    public void setTelefone  ( String telefone ) { this.telefone  = telefone  ; }
    
    public int    getIdUsuario ( ) { return ( idUsuario ); }
    public String getNome      ( ) { return ( nome      ); }
    public String getLogin     ( ) { return ( login     ); }
    public String getSenha     ( ) { return ( senha     ); }
    public String getEmail     ( ) { return ( email     ); }
    public String getTelefone  ( ) { return ( telefone  ); }

    @Override
    public String toString() {
        return ( "Usuario [id=" + idUsuario + ", login=" + login + ", senha=" + senha + ", email=" + email + ", telefone=" + telefone + "]" );
    }

    @Override
    public boolean equals(Object obj) {
        return ( this.getIdUsuario( ) == ((Usuario) obj).getIdUsuario( ) );
    }
} // end class
