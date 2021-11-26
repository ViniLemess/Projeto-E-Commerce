import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    public void deveInstanciarUmPedidoComSucesso() {

        Endereco enderecoPauloMecanica = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contatoPauloMecanica = new Contato("paulo@gmail.com", "555187665540");
        Fornecedor paulo = new Fornecedor(contatoPauloMecanica, enderecoPauloMecanica, "PauloMecanica", "72038484000179");

        Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
        Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");
        Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");

        Produto banco_de_celta = new Produto(1,"Banco de Celta", "Banco acolchoado sem revestimento", 105.99);
        Item item1 = new Item(banco_de_celta, 4);

        Pedido pedido = new Pedido(paulo, vinicius, 23.50, item1);

        Assertions.assertEquals(paulo, pedido.getFornecedor());
        Assertions.assertEquals(vinicius, pedido.getCliente());
        Assertions.assertEquals(23.50, pedido.getValorFrete());
        Assertions.assertEquals(item1, pedido.getItens().indexOf(0));
    }

    @Test
    public void deveValidarInstanciaIncorretaDePedido() {

        try {

            new Pedido(null, null, null, null);
        } catch (Exception exception) {

            Assertions.assertEquals("[Fornecedor deve ser informado!, Cliente deve ser informado!, Valor do frete deve ser informado!]", exception.getMessage());
        }
    }

    @Test
    public void deveObterValorTotalDeItensComSucesso() {

        Endereco enderecoPauloMecanica = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contatoPauloMecanica = new Contato("paulo@gmail.com", "555187665540");
        Fornecedor paulo = new Fornecedor(contatoPauloMecanica, enderecoPauloMecanica, "PauloMecanica", "72038484000179");

        Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
        Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");
        Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");

        Produto banco_de_celta = new Produto("Banco de Celta", "Banco acolchoado sem revestimento", 105.99);
        Item item1 = new Item(banco_de_celta, 4);

        Pedido pedido = new Pedido(paulo, vinicius, 23.50, item1);
        Double valorTotal = pedido.valorTotalItens();

        Assertions.assertEquals(423.96, valorTotal);
    }
    @Test
    public void deveObterValorTotalComSucesso() {

        Endereco enderecoPauloMecanica = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contatoPauloMecanica = new Contato("paulo@gmail.com", "555187665540");
        Fornecedor paulo = new Fornecedor(contatoPauloMecanica, enderecoPauloMecanica, "PauloMecanica", "72038484000179");

        Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
        Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");
        Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");

        Produto banco_de_celta = new Produto("Banco de Celta", "Banco acolchoado sem revestimento", 105.99);
        Item item1 = new Item(banco_de_celta, 4);

        Produto oleo = new Produto("óleo de motor", "Óleo vegetal de motor padrão", 15.99);
        Item item2 = new Item(oleo, 4);

        Pedido pedido = new Pedido(paulo, vinicius, 23.50, item1);
        pedido.addItem(item2);
        Double valorFinal = pedido.valorTotal();

        Assertions.assertEquals(511.41999999999996, valorFinal);
    }
}