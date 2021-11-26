import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    private Connection conexao;

    ProdutoDAO() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projetoecommerce";
            conexao = DriverManager.getConnection(url, "root", "root@!123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduto(Produto model) {

        try {

            String sql = "INSERT INTO produto (nome, descricao, valorUnitario) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, model.getNome());
            preparedStatement.setString(2, model.getDescricao());
            preparedStatement.setDouble(3, model.getValorUnitario());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
