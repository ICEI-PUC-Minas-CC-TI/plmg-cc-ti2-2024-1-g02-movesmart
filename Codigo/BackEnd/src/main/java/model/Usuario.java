package model;

public class Usuario {
    // atributos
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;

    public Usuario() {
        this.id = -1;
        this.login = "";
        this.senha = "";
        this.email = "email@default.com"; // Inicializa com um valor padrão
    } // end Usuario ( )

    // Construtor Alternativo
    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    } // end Usuario ( )

    // Construtor Alternativo
    public Usuario(String login, String senha, String email) {
        setLogin(login);
        setSenha(senha);
        setEmail(email);
    } // end Usuario ( )

    // Retorna o id do usuario
    public int getId() {
        return id;
    } // end getId ( )

    // Atribui um id ao usuario
    public void setId(int id) {
        this.id = id;
    } // end setId ( )

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o login do usuario
    public String getLogin() {
        return login;
    } // end getLogin ( )

    // Atribui um login ao usuario
    public void setLogin(String login) {
        this.login = login;
    } // end setLogin ( )

    // Retorna a senha do usuario
    public String getSenha() {
        return senha;
    } // end getSenha ( )

    // Atribui uma senha ao usuario
    public void setSenha(String senha) {
        this.senha = senha;
    } // end setSenha ( )

    // Retorna o email do usuario
    public String getEmail() {
        return email;
    } // end getEmail ( )

    // Atribui um email ao usuario
    public void setEmail(String email) {
        this.email = email;
    } // end setEmail ( )

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", email=" + email + "]";
    } // end toString ( )

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Usuario) obj).getId());
    } // end equals ( )
} // end class
