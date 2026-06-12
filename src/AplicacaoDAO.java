import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AplicacaoDAO {

    // Registra uma vacinacao
    public void registrarAplicacao(Aplicacao aplicacao) {
        String sql = "INSERT INTO aplicacao (paciente_id, vacina_id, dose, data, observacoes) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, aplicacao.getPacienteId());
            stmt.setInt(2, aplicacao.getVacinaId());
            stmt.setInt(3, aplicacao.getDose());
            stmt.setDate(4, Date.valueOf(aplicacao.getData()));
            stmt.setString(5, aplicacao.getObservacoes());
            stmt.executeUpdate();

            System.out.println("Vacinacao registrada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar vacinacao: " + e.getMessage());
        }
    }

    // Mostra o historico de vacinacao
    public void consultarHistorico(int pacienteId) {
        String sql = "SELECT p.nome AS paciente, v.nome AS vacina, a.dose, a.data, a.observacoes "
                + "FROM aplicacao a "
                + "JOIN paciente p ON a.paciente_id = p.id "
                + "JOIN vacina v ON a.vacina_id = v.id "
                + "WHERE p.id = ? "
                + "ORDER BY a.data";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("Paciente: " + rs.getString("paciente"));
                System.out.println("Vacina: " + rs.getString("vacina"));
                System.out.println("Dose: " + rs.getInt("dose"));
                System.out.println("Data: " + rs.getDate("data"));
                System.out.println("Observacoes: " + rs.getString("observacoes"));
                System.out.println("--------------------------------");
            }

            if (!encontrou) {
                System.out.println("Nenhuma vacinacao encontrada para este paciente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar historico: " + e.getMessage());
        }
    }

    public void mostrarRelatorios() {
        totalDosesAplicadas();
        pacientesVacinados();
        pacientesNaoVacinados();
    }

    public void mostrarConsultasEstatisticas() {
        totalPorRegiao();
        totalPorEscolaridade();
        totalPorFaixaEtaria();
        vacinadoPorDoente();
    }

    private void totalDosesAplicadas() {
        String sql = "SELECT COUNT(*) AS total FROM aplicacao";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Total de doses aplicadas: " + rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatorio de doses: " + e.getMessage());
        }
    }

    private void pacientesVacinados() {
        String sql = "SELECT DISTINCT p.id, p.nome FROM paciente p "
                + "JOIN aplicacao a ON p.id = a.paciente_id "
                + "ORDER BY p.nome";

        System.out.println("\nPacientes vacinados:");
        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatorio de vacinados: " + e.getMessage());
        }
    }

    private void pacientesNaoVacinados() {
        String sql = "SELECT p.id, p.nome FROM paciente p "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "WHERE a.id IS NULL "
                + "ORDER BY p.nome";

        System.out.println("\nPacientes nao vacinados:");
        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatorio de nao vacinados: " + e.getMessage());
        }
    }

    private void totalPorRegiao() {
        String sql = "SELECT r.nome AS regiao, COUNT(DISTINCT a.paciente_id) AS total "
                + "FROM regiao r "
                + "JOIN paciente p ON r.id = p.regiao_id "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "GROUP BY r.nome "
                + "ORDER BY r.nome";

        System.out.println("\nQuantidade de vacinados por regiao:");
        executarConsultaDeTotal(sql, "regiao");
    }

    private void totalPorEscolaridade() {
        String sql = "SELECT e.descricao AS escolaridade, COUNT(DISTINCT a.paciente_id) AS total "
                + "FROM escolaridade e "
                + "JOIN paciente p ON e.id = p.escolaridade_id "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "GROUP BY e.descricao "
                + "ORDER BY e.descricao";

        System.out.println("\nQuantidade de vacinados por escolaridade:");
        executarConsultaDeTotal(sql, "escolaridade");
    }

    private void totalPorFaixaEtaria() {
        String sql = "SELECT "
                + "CASE "
                + "WHEN idade < 18 THEN 'Menor de 18' "
                + "WHEN idade BETWEEN 18 AND 29 THEN '18 a 29' "
                + "WHEN idade BETWEEN 30 AND 59 THEN '30 a 59' "
                + "ELSE '60 ou mais' END AS faixa, "
                + "COUNT(DISTINCT a.paciente_id) AS total "
                + "FROM paciente p "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "GROUP BY faixa "
                + "ORDER BY faixa";

        System.out.println("\nQuantidade de vacinados por faixa etaria:");
        executarConsultaDeTotal(sql, "faixa");
    }

    private void vacinadoPorDoente() {
        String sql = "SELECT "
                + "CASE WHEN p.doente = 1 THEN 'Doente' ELSE 'Nao doente' END AS situacao, "
                + "COUNT(DISTINCT a.paciente_id) AS total_vacinados "
                + "FROM paciente p "
                + "LEFT JOIN aplicacao a ON p.id = a.paciente_id "
                + "GROUP BY p.doente";

        System.out.println("\nVacinado x doente:");
        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("situacao") + ": " + rs.getInt("total_vacinados"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta vacinado x doente: " + e.getMessage());
        }
    }

    private void executarConsultaDeTotal(String sql, String colunaNome) {
        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString(colunaNome) + ": " + rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
    }
}
