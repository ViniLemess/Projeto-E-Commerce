import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection conexao;

    public PedidoDAO() {

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

    public void addPedido(Pedido model) {

        try {

            String sql = "INSERT INTO pedido (dataCompra, id_fornecedor, id_cliente, valorTotalItens, valorFrete) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(model.getDataCompra()));
            preparedStatement.setInt(2, model.getFornecedor().getId());
            preparedStatement.setInt(3, model.getCliente().getId());
            preparedStatement.setDouble(4, model.valorTotalItens());
            preparedStatement.setDouble(5, model.getValorFrete());
            preparedStatement.execute();

            long idDoPedido = 0;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idDoPedido = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            for (Item item : model.getItens()) {

                try {

                    String sql2 = "INSERT INTO item (id_produto, id_pedido, quantidade) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement2 = conexao.prepareStatement(sql2);
                    preparedStatement2.setInt(1, item.getProduto().getId());
                    preparedStatement2.setLong(2, idDoPedido);
                    preparedStatement2.setInt(3, item.getQuantidade());
                    preparedStatement2.execute();


                } catch (SQLException sqlException) {

                    sqlException.printStackTrace();
                }
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }
    }

    public void deletarPedido() {

        try {
            List<String> sqlStrings = new ArrayList<>();
            sqlStrings.add("truncate cliente");
            sqlStrings.add("truncate fornecedor");
            sqlStrings.add("truncate produto");
            sqlStrings.add("truncate item");
            sqlStrings.add("truncate pedido");

            for (String sql : sqlStrings) {

                PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
