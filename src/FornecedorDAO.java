import java.sql.*;

public class FornecedorDAO {

    private Connection conexao;

    public FornecedorDAO() {

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

    public void addFornecedor(Fornecedor model) {

        if (existeFornecedor(model.getCnpj())) {

            throw new IllegalArgumentException("Fornecedor já registrado!");
        } else {

            try {

                String sql = "INSERT INTO fornecedor (nomeFantasia, cnpj, emailFornecedor, telefoneFornecedor, ruaFornecedor," +
                        " numeroFornecedor, bairroFornecedor, complementoFornecedor, cepFornecedor, cidadeFornecedor, estadoFornecedor) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
                PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                preparedStatement.setString(1, model.getNomeFantasia());
                preparedStatement.setString(2, model.getCnpj());
                preparedStatement.setString(3, model.getContato().getEmail());
                preparedStatement.setString(4, model.getContato().getTelefone());
                preparedStatement.setString(5, model.getEndereco().getRua());
                preparedStatement.setString(6, model.getEndereco().getNumero());
                preparedStatement.setString(7, model.getEndereco().getBairro());
                preparedStatement.setString(8, model.getEndereco().getComplemento());
                preparedStatement.setString(9, model.getEndereco().getCep());
                preparedStatement.setString(10, model.getEndereco().getCidade());
                preparedStatement.setString(11, model.getEndereco().getEstado());
                preparedStatement.execute();

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();
            }
        }

    }

    public boolean existeFornecedor(String cnpj) {

        try {

            String sql = "SELECT * FROM fornecedor where cnpj='" + cnpj + "'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultados = preparedStatement.executeQuery();
            return resultados.next();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }

    }
}
