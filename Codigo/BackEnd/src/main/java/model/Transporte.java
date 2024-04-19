package model;

public class Transporte 
{
    // atributos
    private int    id;
    private String status;
    private String horario;
    private String localizacao;

    // Construtor
    public Transporte() {
        this.id = -1;
        this.status = "";
        this.horario = "";
        this.localizacao = "";
    } // end Transporte()

    // Construtor Alternativo
    public Transporte(int id, String status, String horario, String localizacao) {
        setId(id);
        setStatus(status);
        setHorario(horario);
        setLocalizacao(localizacao);
    } // end Transporte()

    // Retorna o id do transporte
    public int getId() {
        return id;
    } // end getId()

    // Atribui um id ao transporte
    public void setId(int id) {
        this.id = id;
    } // end setId()

    // Retorna o status do transporte
    public String getStatus() {
        return status;
    } // end getStatus()

    // Atribui um status ao transporte
    public void setStatus(String status) {
        this.status = status;
    } // end setStatus()

    // Retorna o horario do transporte
    public String getHorario() {
        return horario;
    } // end getHorario()

    // Atribui um horario ao transporte
    public void setHorario(String horario) {
        this.horario = horario;
    } // end setHorario()

    // Retorna a localizacao do transporte
    public String getLocalizacao() {
        return localizacao;
    } // end getLocalizacao()

    // Atribui uma localizacao ao transporte
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    } // end setLocalizacao()

    // 
    @Override
    public String toString() {
        return "Transporte [id=" + id + ", status=" + status + ", horario=" + horario + ", localizacao=" + localizacao + "]";
    } // end toString()

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Transporte) obj).getId());
    } // end equals()

} // end class