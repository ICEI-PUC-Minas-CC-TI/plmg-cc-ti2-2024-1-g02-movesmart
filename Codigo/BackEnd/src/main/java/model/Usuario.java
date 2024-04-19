package model;

public class Usuario {
    // Atributos
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;

    public Usuario() {
        this.login = "";
        this.senha = "";
        this.email = "email@default.com"; // Inicializa com um valor padrão
    }

    // Construtor Alternativo
    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    // Construtor Alternativo
    public Usuario(String login, String senha, String email) {
        setLogin(login);
        setSenha(senha);
        setEmail(email);
    } // end Usuario ( )

    // Construtor Alternativo
    public Usuario(int id, String login, String senha, String email) {
        setId(id);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
    }

    // Retorna o id do usuario
    public int getId() {
        return id;
    }

    // Atribui um id ao usuario
    public void setId(int id) {
        this.id = id;
    }

    // Retorna o nome do usuario
    public String getNome() {
        return nome;
    }

    // Atribui um nome ao usuario
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o login do usuario
    public String getLogin() {
        return login;
    }

    // Atribui um login ao usuario
    public void setLogin(String login) {
        this.login = login;
    }

    // Retorna a senha do usuario
    public String getSenha() {
        return senha;
    }

    // Atribui uma senha ao usuario
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Retorna o email do usuario
    public String getEmail() {
        return email;
    }

    // Atribui um email ao usuario
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Usuario) obj).getId());
    }
}
