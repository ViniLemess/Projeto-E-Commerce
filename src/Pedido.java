import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private LocalDateTime dataCompra;
    private Fornecedor fornecedor;
    private Cliente cliente;
    private Double valorFrete;
    private List<Item> itens = new ArrayList<>();

    public Pedido(Fornecedor fornecedor, Cliente cliente, Double valorFrete, Item item) {
        this.dataCompra = LocalDateTime.now().withNano(0);
        this.fornecedor = fornecedor;
        this.cliente = cliente;
        this.valorFrete = valorFrete;
        addItem(item);
        validar();

    }
    @Deprecated
    public Pedido(LocalDateTime dataCompra, Fornecedor fornecedor, Cliente cliente, Double valorFrete, List<Item> itens) {
        this.dataCompra = dataCompra;
        this.fornecedor = fornecedor;
        this.cliente = cliente;
        this.valorFrete = valorFrete;
        this.itens = itens;
        validar();
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Double valorTotalItens() {

        Double total = 0.0;

        for (Item elemento : itens) {

            total += elemento.valorTotalItem();
        }
        return total;
    }

    public Double valorTotal() {

        double total = valorTotalItens() + this.valorFrete;

        return total;
    }

    public void addItem(Item item) {

        this.itens.add(item);
    }

    private void validar() {

        List<String> mensagensDeErro = new ArrayList<>();

        if (this.fornecedor == null) {

            mensagensDeErro.add("Fornecedor deve ser informado!");
        }
        if (this.cliente == null) {

            mensagensDeErro.add("Cliente deve ser informado!");
        }
        if (valorFrete == null) {

            mensagensDeErro.add("Valor do frete deve ser informado!");
        } else if (this.valorFrete < 0) {

            mensagensDeErro.add("Valor do frete não pode ser negativo!");
        }

        if (!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
