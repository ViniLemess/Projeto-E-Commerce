import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    public void deveInstanciarEnderecoComSucesso() {

        Endereco endereco1 = new Endereco("Sargento", "Jardim Ypu", "9132060", "Porto Alegre", "RS");

        Assertions.assertEquals("Sargento", endereco1.getRua());
        Assertions.assertEquals("Jardim Ypu", endereco1.getBairro());
        Assertions.assertEquals("9132060", endereco1.getCep());
        Assertions.assertEquals("Porto Alegre", endereco1.getCidade());
        Assertions.assertEquals("RS", endereco1.getEstado());
    }

    @Test
    public void deveValidarInstanciaIncorretaDeEndereco() {

        try {

            new Endereco(null, null, null, null, null);
        } catch (Exception exception) {

            Assertions.assertEquals("[Rua deve ser informada!, Bairro deve ser informado!, Cep deve ser informado!, Cidade deve ser informada!, estado deve ser informado!]", exception.getMessage());
        }
    }
}