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

        pedidoDAO = new PedidoDAO();
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
    }
}