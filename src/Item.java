import java.util.ArrayList;
import java.util.List;

public class Item {

    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        validar();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double valorTotalItem() {

        double total = this.produto.getValorUnitario() * this.quantidade;
        return total;
    }

    public void validar() {

        List<String> mensagensDeErro = new ArrayList<>();

        if (this.produto == null) {

            mensagensDeErro.add("Produto deve ser Informado!");
        }
        if (this.quantidade <= 0) {

            mensagensDeErro.add("Deve haver pelo menos 1 item!");
        }
        if (!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
