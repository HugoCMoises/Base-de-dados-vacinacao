import java.time.LocalDate;

public class Vacina {
    private int id;
    private String nome;
    private String fabricante;
    private String tipo;
    private int quantidadeDoses;
    private String lote;
    private LocalDate dataAplicacao;

    public Vacina() {
    }

    public Vacina(String nome, String fabricante, String tipo, int quantidadeDoses, String lote,
                  LocalDate dataAplicacao) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.tipo = tipo;
        this.quantidadeDoses = quantidadeDoses;
        this.lote = lote;
        this.dataAplicacao = dataAplicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidadeDoses() {
        return quantidadeDoses;
    }

    public String getLote() {
        return lote;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }
}
