import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Pedido> buscarTodosPedidos() {

        List<Pedido> pedidos = new ArrayList<>();
        try {

            String sql = "select * from pedido p " +
                    "inner join fornecedor f on f.id_fornecedor = p.id_fornecedor " +
                    "inner join cliente c on c.id_cliente = p.id_cliente " +
                    "inner join item i on i.id_pedido = p.id_pedido " +
                    "inner join produto pr on pr.id_produto = i.id_produto";
            Statement preparedStatement = conexao.createStatement();
            ResultSet resultados = preparedStatement.executeQuery(sql);

            List<Item> itens = new ArrayList<>();
            LocalDateTime dataCompra = null;
            double valorFrete = 0;
            Cliente cliente = null;
            Fornecedor fornecedor = null;

            while (resultados.next()) {

                //pedido
                dataCompra = resultados.getObject("dataCompra", LocalDateTime.class);
                valorFrete = resultados.getDouble("valorFrete");
                //fornecedor
                var nomeFantasia = resultados.getString("nomeFantasia");
                var cnpj = resultados.getString("cnpj");
                var emailFornecedor = resultados.getString("emailFornecedor");
                var telefoneFornecedor = resultados.getString("telefoneFornecedor");
                var ruaFornecedor = resultados.getString("ruaFornecedor");
                var numeroFornecedor = resultados.getString("numeroFornecedor");
                var bairroFornecedor = resultados.getString("bairroFornecedor");
                var complementoFornecedor = resultados.getString("complementoFornecedor");
                var cepFornecedor = resultados.getString("cepFornecedor");
                var cidadeFornecedor = resultados.getString("cidadeFornecedor");
                var estadoFornecedor = resultados.getString("estadoFornecedor");
                //cliente
                var nomeCliente = resultados.getString("nomeCliente");
                var cpf = resultados.getString("cpf");
                var emailCliente = resultados.getString("emailCliente");
                var telefoneCliente = resultados.getString("telefoneCliente");
                var ruaCliente = resultados.getString("ruaCliente");
                var numeroCliente = resultados.getString("numeroCliente");
                var bairroCliente = resultados.getString("bairroCliente");
                var complementoCliente = resultados.getString("complementoCliente");
                var cepCliente = resultados.getString("cepCliente");
                var cidadeCliente = resultados.getString("cidadeCliente");
                var estadoCliente = resultados.getString("estadoCliente");

                Contato contatoFornecedor = new Contato(emailFornecedor, telefoneFornecedor);
                Endereco enderecoFornecedor = new Endereco(ruaFornecedor, numeroFornecedor, bairroFornecedor, complementoFornecedor, cepFornecedor, cidadeFornecedor, estadoFornecedor);
                fornecedor = new Fornecedor(contatoFornecedor, enderecoFornecedor, nomeFantasia, cnpj);

                Contato contatoCliente = new Contato(emailCliente, telefoneCliente);
                Endereco enderecoCliente = new Endereco(ruaCliente, numeroCliente, bairroCliente, complementoCliente, cepCliente, cidadeCliente, estadoCliente);
                cliente = new Cliente(contatoCliente, enderecoCliente, nomeCliente, cpf);
                //produto
                var quantidade = resultados.getInt("quantidade");
                var nomeProduto = resultados.getString("nomeProduto");
                var descricaoProduto = resultados.getString("descricao");
                var valorUnitario = resultados.getDouble("valorUnitario");
                Produto produto = new Produto(nomeProduto, descricaoProduto, valorUnitario);
                Item item = new Item(produto, quantidade);
                itens.add(item);

            }
            pedidos.add(new Pedido(dataCompra, fornecedor, cliente, valorFrete, itens));

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }
        if (pedidos.isEmpty()) {

            throw new IllegalArgumentException("Nenhum pedido encontrado!");
        }
        return pedidos;

    }

    public Pedido buscarPedidoPorId(int id) {

        try {
            String sql = "select * from pedido p " +
                    "inner join fornecedor f on f.id_fornecedor = p.id_fornecedor " +
                    "inner join cliente c on c.id_cliente = p.id_cliente " +
                    "inner join item i on i.id_pedido = p.id_pedido " +
                    "inner join produto pr on pr.id_produto = i.id_produto WHERE p.id_pedido=" + id;
            Statement preparedStatement = conexao.createStatement();
            ResultSet resultados = preparedStatement.executeQuery(sql);

            List<Item> itens = new ArrayList<>();
            LocalDateTime dataCompra = null;
            double valorFrete = 0;
            Cliente cliente = null;
            Fornecedor fornecedor = null;

            while (resultados.next()) {

                //pedido
                dataCompra = resultados.getObject("dataCompra", LocalDateTime.class);
                valorFrete = resultados.getDouble("valorFrete");
                //fornecedor
                var nomeFantasia = resultados.getString("nomeFantasia");
                var cnpj = resultados.getString("cnpj");
                var emailFornecedor = resultados.getString("emailFornecedor");
                var telefoneFornecedor = resultados.getString("telefoneFornecedor");
                var ruaFornecedor = resultados.getString("ruaFornecedor");
                var numeroFornecedor = resultados.getString("numeroFornecedor");
                var bairroFornecedor = resultados.getString("bairroFornecedor");
                var complementoFornecedor = resultados.getString("complementoFornecedor");
                var cepFornecedor = resultados.getString("cepFornecedor");
                var cidadeFornecedor = resultados.getString("cidadeFornecedor");
                var estadoFornecedor = resultados.getString("estadoFornecedor");
                //cliente
                var nomeCliente = resultados.getString("nomeCliente");
                var cpf = resultados.getString("cpf");
                var emailCliente = resultados.getString("emailCliente");
                var telefoneCliente = resultados.getString("telefoneCliente");
                var ruaCliente = resultados.getString("ruaCliente");
                var numeroCliente = resultados.getString("numeroCliente");
                var bairroCliente = resultados.getString("bairroCliente");
                var complementoCliente = resultados.getString("complementoCliente");
                var cepCliente = resultados.getString("cepCliente");
                var cidadeCliente = resultados.getString("cidadeCliente");
                var estadoCliente = resultados.getString("estadoCliente");

                Contato contatoFornecedor = new Contato(emailFornecedor, telefoneFornecedor);
                Endereco enderecoFornecedor = new Endereco(ruaFornecedor, numeroFornecedor, bairroFornecedor, complementoFornecedor, cepFornecedor, cidadeFornecedor, estadoFornecedor);
                fornecedor = new Fornecedor(contatoFornecedor, enderecoFornecedor, nomeFantasia, cnpj);

                Contato contatoCliente = new Contato(emailCliente, telefoneCliente);
                Endereco enderecoCliente = new Endereco(ruaCliente, numeroCliente, bairroCliente, complementoCliente, cepCliente, cidadeCliente, estadoCliente);
                cliente = new Cliente(contatoCliente, enderecoCliente, nomeCliente, cpf);
                //produto
                var quantidade = resultados.getInt("quantidade");
                var nomeProduto = resultados.getString("nomeProduto");
                var descricaoProduto = resultados.getString("descricao");
                var valorUnitario = resultados.getDouble("valorUnitario");
                Produto produto = new Produto(nomeProduto, descricaoProduto, valorUnitario);
                Item item = new Item(produto, quantidade);
                itens.add(item);

            }
            return new Pedido(dataCompra, fornecedor, cliente, valorFrete, itens);

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        }
        throw  new IllegalArgumentException("Pedido não encontrado!");
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
