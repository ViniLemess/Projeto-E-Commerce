
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    public void deveInstanciarProdutoComSucesso() {

        Produto camiseta = new Produto("T-Shirt", 29.99);

        Assertions.assertEquals("T-Shirt", camiseta.getNome());
        Assertions.assertEquals(29.99, camiseta.getValorUnitario());
    }

    @Test
    public void deveValidarInstanciaDeProdutoComErro() {

        try {

            new Produto(null, -1.0);
        } catch (Exception exception) {

            Assertions.assertEquals("[Nome deve ser informado!, Valor Unitario não pode ser negativo!]", exception.getMessage());
        }
    }
    @Test
    public void deveValidarInstanciaDeProdutoComCaracteresExcedidos() {

        try {

            Helper helper = new Helper();
            String palavraGigante = helper.gerarPalavra(501);
            new Produto(palavraGigante, palavraGigante, 50.0);
        } catch (Exception exception) {

            Assertions.assertEquals("[Numero de caracteres de nome excedido!, Numero de caracteres de descricao excedido!]", exception.getMessage());
        }
    }
}