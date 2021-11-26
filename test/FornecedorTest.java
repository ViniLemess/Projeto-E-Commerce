import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorTest {

    @Test
    public void deveInstanciarFornecedorComSucesso() {

        Endereco endereco1 = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contato1 = new Contato("paulo@gmail.com", "555187665540");

        Fornecedor paulo = new Fornecedor(contato1, endereco1, "PauloMecanica", "72038484000179");

        Assertions.assertEquals(contato1, paulo.getContato());
        Assertions.assertEquals(endereco1, paulo.getEndereco());
        Assertions.assertEquals("PauloMecanica", paulo.getNomeFantasia());
        Assertions.assertEquals("72038484000179", paulo.getCnpj());
    }

    @Test
    public void deveValidarInstanciaDeFornecedorComErro() {

        try {

            new Fornecedor(null, null, null, null);
        } catch (Exception exception) {

            Assertions.assertEquals("[Endereço deve ser informado!, Contato deve ser informado!, Nome Fantasia deve ser informado!, CNPJ deve ser informado!]", exception.getMessage());
        }
    }

    @Test
    public void deveValidarInstanciaDeFornecedorComCaracteresExcedidos() {

        try {

            Endereco endereco1 = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
            Contato contato1 = new Contato("paulo@gmail.com", "555187665540");

            String palavraGigante = Helper.gerarPalavra(200);

            new Fornecedor(contato1, endereco1, palavraGigante, palavraGigante);
        } catch (Exception exception) {

            Assertions.assertEquals("[Número de caracteres de nome fantasia excedido!, CNPJ Inválido]", exception.getMessage());
        }
    }

}