import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VacinaDAO {

    // Salva a vacina
    public void salvarVacina(Vacina vacina) {
        String sql = "INSERT INTO vacina (nome, fabricante, tipo, quantidade_doses, lote, data_aplicacao) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setString(3, vacina.getTipo());
            stmt.setInt(4, vacina.getQuantidadeDoses());
            stmt.setString(5, vacina.getLote());
            stmt.setDate(6, Date.valueOf(vacina.getDataAplicacao()));
            stmt.executeUpdate();

            System.out.println("Vacina salva com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar vacina: " + e.getMessage());
        }
    }

    public void listarVacinas() {
        String sql = "SELECT * FROM vacina ORDER BY nome";

        try (Connection conexao = DatabaseConnection.abrirConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Fabricante: " + rs.getString("fabricante"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("Quantidade de doses: " + rs.getInt("quantidade_doses"));
                System.out.println("Lote: " + rs.getString("lote"));
                System.out.println("Data de aplicacao: " + rs.getDate("data_aplicacao"));
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar vacinas: " + e.getMessage());
        }
    }
}
