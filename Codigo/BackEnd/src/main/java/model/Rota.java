package model;

public class Rota 
{
    // atributos
    private int    id;
    private String pontos_parada;
    private String horario;
    private String transporte_associado;

    // Construtor
    public Rota() {
        this.id = -1;
        this.pontos_parada = "";
        this.horario = "";
        this.transporte_associado = "";
    } // end Rota()

    // Construtor Alternativo
    public Rota(int id, String pontos_parada, String horario, String transporte_associado) {
        setId(id);
        setPontos_parada(pontos_parada);
        setHorario(horario);
        setTransporte_associado(transporte_associado);
    } // end Rota()

    // Retorna o id do transporte
    public int getId() {
        return id;
    } // end getId()

    // Atribui um id ao transporte
    public void setId(int id) {
        this.id = id;
    } // end setId()

    // Retorna o pontos_parada do transporte
    public String getStatus() {
        return pontos_parada;
    } // end getStatus()

    // Atribui um pontos_parada ao transporte
    public void setPontos_parada(String pontos_parada) {
        this.pontos_parada = pontos_parada;
    } // end setPontos_parada()

    // Retorna o horario do transporte
    public String getHorario() {
        return horario;
    } // end getHorario()

    // Atribui um horario ao transporte
    public void setHorario(String horario) {
        this.horario = horario;
    } // end setHorario()

    // Retorna a transporte_associado do transporte
    public String getLocalizacao() {
        return transporte_associado;
    } // end getLocalizacao()

    // Atribui uma transporte_associado ao transporte
    public void setTransporte_associado(String transporte_associado) {
        this.transporte_associado = transporte_associado;
    } // end setTransporte_associado()

    // 
    @Override
    public String toString() {
        return "Rota [id=" + id + ", pontos_parada=" + pontos_parada + ", horario=" + horario + ", transporte_associado=" + transporte_associado + "]";
    } // end toString()

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Rota) obj).getId());
    } // end equals()

} // end class