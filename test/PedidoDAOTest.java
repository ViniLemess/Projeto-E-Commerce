import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDAOTest {

    private Fornecedor paulo;
    private Cliente vinicius;
    private Produto banco_de_celta;
    private Produto vidro_de_celta;
    private PedidoDAO pedidoDAO;

    @BeforeEach
    public void init() {

        pedidoDAO = new PedidoDAO();
        pedidoDAO.deletarPedido();

        Endereco enderecoPauloMecanica = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contatoPauloMecanica = new Contato("paulo@gmail.com", "555187665540");
        paulo = new Fornecedor(1 ,contatoPauloMecanica, enderecoPauloMecanica, "PauloMecanica", "72038484000179");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.addFornecedor(paulo);

        Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
        Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");
        vinicius = new Cliente(1, contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.addCliente(vinicius);

        banco_de_celta = new Produto(1,"Banco de Celta", "Banco acolchoado sem revestimento", 105.99);
        vidro_de_celta = new Produto(2,"Vidro de celta", "vidro temperado frontal de celta", 60.50);
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtoDao.addProduto(banco_de_celta);
        produtoDao.addProduto(vidro_de_celta);
    }
    @AfterEach
    public void excluirDados() {

        pedidoDAO.deletarPedido();
    }

    @Test
    public void deveAdicionarPedidoAoBancoDeDadosComSucesso() {

        Item item1 = new Item(banco_de_celta, 2);
        Item item2 = new Item(vidro_de_celta, 1);

        Pedido pedido = new Pedido(paulo, vinicius, 23.50, item1);
        pedido.addItem(item2);

        pedidoDAO.addPedido(pedido);

        var pedidoBuscado = pedidoDAO.buscarPedidoPorId(1);

        //cliente
        Assertions.assertEquals(pedido.getCliente().getNome(), pedidoBuscado.getCliente().getNome());
        Assertions.assertEquals(pedido.getCliente().getCpf(), pedidoBuscado.getCliente().getCpf());
        Assertions.assertEquals(pedido.getCliente().getContato().getEmail(), pedidoBuscado.getCliente().getContato().getEmail());
        Assertions.assertEquals(pedido.getCliente().getContato().getTelefone(), pedidoBuscado.getCliente().getContato().getTelefone());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getRua(), pedidoBuscado.getCliente().getEndereco().getRua());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getNumero(), pedidoBuscado.getCliente().getEndereco().getNumero());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getBairro(), pedidoBuscado.getCliente().getEndereco().getBairro());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getComplemento(), pedidoBuscado.getCliente().getEndereco().getComplemento());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getCep(), pedidoBuscado.getCliente().getEndereco().getCep());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getCidade(), pedidoBuscado.getCliente().getEndereco().getCidade());
        Assertions.assertEquals(pedido.getCliente().getEndereco().getEstado(), pedidoBuscado.getCliente().getEndereco().getEstado());
        //fornecedor
        Assertions.assertEquals(pedido.getFornecedor().getNomeFantasia(), pedidoBuscado.getFornecedor().getNomeFantasia());
        Assertions.assertEquals(pedido.getFornecedor().getCnpj(), pedidoBuscado.getFornecedor().getCnpj());
        Assertions.assertEquals(pedido.getFornecedor().getContato().getEmail(), pedidoBuscado.getFornecedor().getContato().getEmail());
        Assertions.assertEquals(pedido.getFornecedor().getContato().getTelefone(), pedidoBuscado.getFornecedor().getContato().getTelefone());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getRua(), pedidoBuscado.getFornecedor().getEndereco().getRua());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getNumero(), pedidoBuscado.getFornecedor().getEndereco().getNumero());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getBairro(), pedidoBuscado.getFornecedor().getEndereco().getBairro());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getComplemento(), pedidoBuscado.getFornecedor().getEndereco().getComplemento());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getCep(), pedidoBuscado.getFornecedor().getEndereco().getCep());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getCidade(), pedidoBuscado.getFornecedor().getEndereco().getCidade());
        Assertions.assertEquals(pedido.getFornecedor().getEndereco().getEstado(), pedidoBuscado.getFornecedor().getEndereco().getEstado());
        //item
        for (int i = 0; i < pedido.getItens().size(); i++) {
            Assertions.assertEquals(pedido.getItens().get(i).getProduto().getNome(), pedidoBuscado.getItens().get(i).getProduto().getNome());
            Assertions.assertEquals(pedido.getItens().get(i).getProduto().getDescricao(), pedidoBuscado.getItens().get(i).getProduto().getDescricao());
            Assertions.assertEquals(pedido.getItens().get(i).getProduto().getValorUnitario(), pedidoBuscado.getItens().get(i).getProduto().getValorUnitario());
        }
        //pedido
        Assertions.assertEquals(pedido.getDataCompra(), pedidoBuscado.getDataCompra());
        Assertions.assertEquals(pedido.getValorFrete(), pedidoBuscado.getValorFrete());
    }
    @Test
    public void deveBuscarTodosPedidosComSucesso() {

        Item item1 = new Item(banco_de_celta, 2);
        Item item2 = new Item(vidro_de_celta, 1);

        Pedido pedido = new Pedido(paulo, vinicius, 23.50, item1);
        pedido.addItem(item2);

        pedidoDAO.addPedido(pedido);

        var pedidosBuscados = pedidoDAO.buscarTodosPedidos();

        Assertions.assertEquals(1, pedidosBuscados.size());
    }
}