
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    public void deveInstanciarProdutoComSucesso() {

        Produto camiseta = new Produto("T-Shirt", "Camisa preta", 29.99);

        Assertions.assertEquals("T-Shirt", camiseta.getNome());
        Assertions.assertEquals("Camisa preta", camiseta.getDescricao());
        Assertions.assertEquals(29.99, camiseta.getValorUnitario());
    }

    @Test
    public void deveValidarInstanciaDeProdutoComErro() {

        try {

            new Produto(null, "", -1.0);
        } catch (Exception exception) {

            Assertions.assertEquals("[Nome deve ser informado!, Descrição deve ser informada!, Valor Unitario não pode ser negativo!]", exception.getMessage());
        }
    }
    @Test
    public void deveValidarInstanciaDeProdutoComCaracteresExcedidos() {

        try {

            String palavraGigante = Helper.gerarPalavra(501);
            new Produto(palavraGigante, palavraGigante, 50.0);
        } catch (Exception exception) {

            Assertions.assertEquals("[Número de caracteres nome excedido!, Número de caracteres  de desçricao excedido!]", exception.getMessage());
        }
    }
}