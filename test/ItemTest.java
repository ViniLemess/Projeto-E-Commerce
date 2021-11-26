import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    public void deveInstanciarItemComSucesso() {

        Produto camiseta = new Produto("T-Shirt", "Camiseta preta", 29.99);
        Item item1 = new Item(camiseta, 5);

        Assertions.assertEquals(camiseta, item1.getProduto());
        Assertions.assertEquals(5, item1.getQuantidade());
    }
    @Test
    public void deveValidarInstanciaDeItemComErro() {

        try {

            new Item(null, 0);
        } catch (Exception exception) {

            Assertions.assertEquals("[Produto deve ser informado!, Deve haver pelo menos 1 item!]", exception.getMessage());
        }
    }
    @Test
    public void deveValidarMetodoGetValorTotalItens() {

        Produto camiseta = new Produto("T-Shirt", "Camiseta preta", 29.99);
        Item item1 = new Item(camiseta, 5);

        Double valorTotal = item1.valorTotalItem();

        Assertions.assertEquals(149.95, valorTotal);
    }
}