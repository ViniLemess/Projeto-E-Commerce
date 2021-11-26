import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int Id;
    private String nome;
    private String descricao;
    private Double valorUnitario;

    public Produto(String nome, String descricao, Double valorUnitario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        validar();
    }
    public Produto(int Id, String nome, String descricao, Double valorUnitario) {
        this.Id = Id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        validar();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return Id;
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
        if (this.descricao == null || this.descricao.isBlank()) {

            mensagensDeErro.add("Descrição deve ser informada!");
        } else if (this.descricao.length() > 500) {

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
