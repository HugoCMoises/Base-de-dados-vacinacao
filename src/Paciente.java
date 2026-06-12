public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private String sexo;
    private String cpf;
    private String telefone;
    private String endereco;
    private String cidade;
    private int regiaoId;
    private int escolaridadeId;
    private boolean doente;

    public Paciente() {
    }

    public Paciente(String nome, int idade, String sexo, String cpf, String telefone, String endereco,
                    String cidade, int regiaoId, int escolaridadeId, boolean doente) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.regiaoId = regiaoId;
        this.escolaridadeId = escolaridadeId;
        this.doente = doente;
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

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public int getRegiaoId() {
        return regiaoId;
    }

    public int getEscolaridadeId() {
        return escolaridadeId;
    }

    public boolean isDoente() {
        return doente;
    }
}
