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
    public void deveValidarInstanciaNulaDeCliente() {

        try {
            Endereco enderecoVinicius = new Endereco(null, null, null, null, null);
            Contato contatoVinicius = new Contato(null, null);
            Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, null, null);
        } catch (Exception exception) {

            Assertions.assertEquals("[Nome deve ser informado!, CPF deve ser informado!, Rua deve ser informada!, Bairro deve ser informado!, CEP deve ser informado!, Cidade deve ser informada!, Estado deve ser informado!, Email deve ser informado!, Telefone deve ser informado!]", exception.getMessage());
        }
    }

    @Test
    public void deveValidarInstanciaComTamanhoDeCamposInvalidos() {

        try {

            Helper stringGigante = new Helper();

            String palavraGigante = stringGigante.gerarPalavra(101);
            System.out.println(palavraGigante);
            Endereco enderecoVinicius = new Endereco(palavraGigante, palavraGigante, palavraGigante, palavraGigante, palavraGigante);
            Contato contatoVinicius = new Contato(palavraGigante, palavraGigante);

            Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, palavraGigante, palavraGigante);
        } catch (Exception exception) {

            Assertions.assertEquals("[Número de caracteres de nome excedido!, CPF Inválido, Número de caracteres de rua excedido!, Número de caracteres de bairro excedido!, Número de caracteres de CEP excedido!, Número de caracteres de cidade excedido!, Número de caracteres de estado inválido!, Número de caracteres de email excedido!, Número de caracteres de telefone excedido!]", exception.getMessage());
        }
    }
}