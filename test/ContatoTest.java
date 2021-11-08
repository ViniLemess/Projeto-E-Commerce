import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import static org.junit.jupiter.api.Assertions.*;

class ContatoTest {

    @Test
    public void deveInstanciarContatoComSucesso() {

        Contato contatoVini = new Contato("vini@gmail.com", "5551941556177");

        Assertions.assertEquals("vini@gmail.com", contatoVini.getEmail());
        Assertions.assertEquals("5551941556177", contatoVini.getTelefone());
    }

    @Test
    public void deveValidarInstanciaIncorretaDeContato() {

        try {

            new Contato(null, null);
        } catch (Exception exception) {

            Assertions.assertEquals("[Email deve ser informado!, Telefone deve ser informado!]", exception.getMessage());
        }
    }
}