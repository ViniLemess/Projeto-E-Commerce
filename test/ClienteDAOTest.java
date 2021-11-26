import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {

   @Test
   public void deveAdicionarClienteAoBancoDeDadosComSucesso() {

       Endereco enderecoVinicius = new Endereco("Sargento", "Vila Jardim", "91342005", "Porto Alegre", "RS");
       Contato contatoVinicius = new Contato("vinicius@gmail.com", "555198465629");

       Cliente vinicius = new Cliente(contatoVinicius, enderecoVinicius, "Vinicius", "77788860096");

       ClienteDAO tabelaCliente = new ClienteDAO();

       tabelaCliente.addCliente(vinicius);
   }

}