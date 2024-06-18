package model;

public class Notificacao {
    private String mensagem;
    private String data;
    private String hora;
    private String idUsuario;

    public Notificacao(String mensagem, String data, String hora, String idUsuario) {
        this.mensagem = mensagem;
        this.data = data;
        this.hora = hora;
        this.idUsuario = idUsuario;
    }

    public Notificacao(){}

    public String getMensagem() { return mensagem; }

    public String getData() { return data; }

    public String getHora() { return hora; }

    public String getIdUsuario() { return idUsuario; }

    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public void setData(String data) { this.data = data; }

    public void setHora(String hora) { this.hora = hora; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
}