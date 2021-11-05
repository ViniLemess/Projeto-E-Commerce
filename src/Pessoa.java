import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {

    private Contato contato;
    private Endereco endereco;
    protected List<String> mensagensDeErro = new ArrayList<>();

    public Pessoa(Contato contato, Endereco endereco) {
        this.contato = contato;
        this.endereco = endereco;
        validacaoPai();
    }

    public Contato getContato() {
        return contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    protected abstract void validar();

    private void validacaoPai() {

        if (this.endereco == null) {

            mensagensDeErro.add("Endereço deve ser informado!");
        }
        if (this.contato == null) {

            mensagensDeErro.add("Contato deve ser informado!");
        }
        if (!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
