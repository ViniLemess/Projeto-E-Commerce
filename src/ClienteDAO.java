import java.sql.*;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {

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

    public void addCliente(Cliente model) {

        if (existeCliente(model.getCpf())) {

           throw new IllegalArgumentException("Cliente já cadastrado!");
        } else {

            try {

                String sql = "INSERT INTO cliente (nome, cpf, email, telefone, rua, numero, bairro, complemento, cep, cidade, estado) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
                PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                preparedStatement.setString(1, model.getNome());
                preparedStatement.setString(2, model.getCpf());
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

    public boolean existeCliente(String cpf) {

        try {
            String sql = "SELECT * FROM cliente WHERE cpf='" + cpf + "'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultados = preparedStatement.executeQuery();
            return resultados.next();

        } catch (SQLException sqlException) {

            throw new RuntimeException(sqlException.getMessage());
        }
    }
}
