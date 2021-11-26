import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorDAOTest {

    @Test
    public void deveAdicionarFornecedorAoBancoDeDadosComSucesso() {

        Endereco endereco1 = new Endereco("Sargento", "Petropolis", "9232086", "Porto Alegre", "RS");
        Contato contato1 = new Contato("paulo@gmail.com", "555187665540");

        Fornecedor paulo = new Fornecedor(contato1, endereco1, "PauloMecanica", "72038484000179");

        FornecedorDAO tabelaFornecedor = new FornecedorDAO();
        tabelaFornecedor.addFornecedor(paulo);
    }
}