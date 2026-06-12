import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    // Salva o paciente
    public void salvarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, idade, sexo, cpf, telefone, endereco, cidade, regiao_id, escolaridade_id, doente) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getEndereco());
            stmt.setString(7, paciente.getCidade());
            stmt.setInt(8, paciente.getRegiaoId());
            stmt.setInt(9, paciente.getEscolaridadeId());
            stmt.setBoolean(10, paciente.isDoente());
            stmt.executeUpdate();

            System.out.println("Paciente salvo com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar paciente: " + e.getMessage());
        }
    }

    // Busca os dados do paciente
    public void buscarPaciente(String cpf) {
        String sql = "SELECT p.id, p.nome, p.idade, p.sexo, p.cpf, p.telefone, p.endereco, p.cidade, "
                + "r.nome AS regiao, e.descricao AS escolaridade, p.doente "
                + "FROM paciente p "
                + "JOIN regiao r ON p.regiao_id = r.id "
                + "JOIN escolaridade e ON p.escolaridade_id = e.id "
                + "WHERE p.cpf = ?";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mostrarPaciente(rs);
            } else {
                System.out.println("Paciente nao encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
    }

    // Lista todos os pacientes
    public void listarPacientes() {
        String sql = "SELECT p.id, p.nome, p.idade, p.sexo, p.cpf, p.telefone, p.endereco, p.cidade, "
                + "r.nome AS regiao, e.descricao AS escolaridade, p.doente "
                + "FROM paciente p "
                + "JOIN regiao r ON p.regiao_id = r.id "
                + "JOIN escolaridade e ON p.escolaridade_id = e.id "
                + "ORDER BY p.id";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mostrarPaciente(rs);
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public void listarPacientesNaoVacinados() {
        String sql = "SELECT p.id, p.nome, p.idade, p.sexo, p.cpf, p.telefone, p.endereco, p.cidade, "
                + "r.nome AS regiao, e.descricao AS escolaridade, p.doente "
                + "FROM paciente p "
                + "JOIN regiao r ON p.regiao_id = r.id "
                + "JOIN escolaridade e ON p.escolaridade_id = e.id "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "WHERE a.id IS NULL "
                + "ORDER BY p.nome";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mostrarPaciente(rs);
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar nao vacinados: " + e.getMessage());
        }
    }

    private void mostrarPaciente(ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("Idade: " + rs.getInt("idade"));
        System.out.println("Sexo: " + rs.getString("sexo"));
        System.out.println("CPF: " + rs.getString("cpf"));
        System.out.println("Telefone: " + rs.getString("telefone"));
        System.out.println("Endereco: " + rs.getString("endereco"));
        System.out.println("Cidade: " + rs.getString("cidade"));
        System.out.println("Regiao: " + rs.getString("regiao"));
        System.out.println("Escolaridade: " + rs.getString("escolaridade"));
        System.out.println("Doente: " + (rs.getBoolean("doente") ? "Sim" : "Nao"));
    }
}
