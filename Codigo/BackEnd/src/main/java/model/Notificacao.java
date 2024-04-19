package model;

public class Notificacao 
{
    // atributos
    private int    id;
    private String tipo;
    private String mensagem;
    private long   timestamp;

    // Construtor
    public Notificacao() {
        this.id = -1;
        this.tipo = "";
        this.mensagem = "";
        this.timestamp = 0;
    } // end Notificacao()

    // Construtor Alternativo
    public Notificacao(int id, String tipo, String mensagem, long timestamp) {
        setId(id);
        setTipo(tipo);
        setMensagem(mensagem);
        setTimestamp(timestamp);
    } // end Notificacao()

    // Retorna o id da notificacao
    public int getId() {
        return id;
    } // end getId()

    // Atribui um id à notificacao
    public void setId(int id) {
        this.id = id;
    } // end setId()

    // Retorna o tipo da notificacao
    public String getTipo() {
        return tipo;
    } // end getTipo()

    // Atribui um tipo à notificacao
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } // end setTipo()

    // Retorna a mensagem da notificacao
    public String getMensagem() {
        return mensagem;
    } // end getMensagem()

    // Atribui uma mensagem à notificacao
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    } // end setMensagem()

    // Retorna o timestamp da notificacao
    public long getTimestamp() {
        return timestamp;
    } // end getTimestamp()

    // Atribui um timestamp à notificacao
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    } // end setTimestamp()

    // 
    @Override
    public String toString() {
        return "Notificacao [id=" + id + ", tipo=" + tipo + ", mensagem=" + mensagem + ", timestamp=" + timestamp + "]";
    } // end toString()

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Notificacao) obj).getId());
    } // end equals()

} // end class
