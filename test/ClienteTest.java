import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    public void deveInstanciarClienteComSucesso() {

        Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
        Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");

        Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");

        Assertions.assertEquals(enderecoVinicius, vinicius.getEndereco());
        Assertions.assertEquals(contatoVinicius, vinicius.getContato());
        Assertions.assertEquals("Vinicius", vinicius.getNome());
        Assertions.assertEquals("77788860096", vinicius.getCpf());
    }

    @Test
    public void deveValidarInstanciaComTamanhoDeCamposInvalidos() {


    }
}