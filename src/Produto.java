import java.util.ArrayList;
import java.util.List;

public class Produto {

    private String nome;
    private String descricao;
    private Double valorUnitario;

    public Produto(String nome, String descricao, Double valorUnitario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        validar();
    }
    public Produto(String nome, Double valorUnitario) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        validar();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    private void validar() {

        List<String> mensagensDeErro = new ArrayList<>();

        if (this.nome == null || this.nome.isBlank()) {

            mensagensDeErro.add("Nome deve ser informado!");
        } else if (this.nome.length() > 100) {

            mensagensDeErro.add("Número de caracteres nome excedido!");
        }
        if (this.descricao != null && this.descricao.length() > 500) {

            mensagensDeErro.add("Número de caracteres  de desçricao excedido!");
        }
        if (this.valorUnitario < 0) {

            mensagensDeErro.add("Valor Unitario não pode ser negativo!");
        }

        if (!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
