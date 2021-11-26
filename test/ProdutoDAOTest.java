import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDAOTest {

    @Test
    public void deveAdicionarProdutoAoBancoDeDadosComSucesso() {

        Produto banco_de_celta = new Produto("Banco de Celta","Banco acolchoado sem revestimento", 105.99);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.addProduto(banco_de_celta);

    }
}