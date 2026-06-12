import java.time.LocalDate;

public class Aplicacao {
    private int id;
    private int pacienteId;
    private int vacinaId;
    private int dose;
    private LocalDate data;
    private String observacoes;

    public Aplicacao() {
    }

    public Aplicacao(int pacienteId, int vacinaId, int dose, LocalDate data, String observacoes) {
        this.pacienteId = pacienteId;
        this.vacinaId = vacinaId;
        this.dose = dose;
        this.data = data;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public int getVacinaId() {
        return vacinaId;
    }

    public int getDose() {
        return dose;
    }

    public LocalDate getData() {
        return data;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
