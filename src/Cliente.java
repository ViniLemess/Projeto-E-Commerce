import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

    private String nome;
    private String cpf;

    public Cliente(Contato contato, Endereco endereco, String nome, String cpf) {
        super(contato, endereco);
        this.nome = nome;
        this.cpf = cpf;
        validar();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    protected void validar() {

        if (this.nome == null || this.nome.isBlank()) {

            mensagensDeErro.add("Nome deve ser informado!");
        } else if (this.nome.length() > 100) {

            mensagensDeErro.add("Número de caracteres de nome excedido!");
        }
        if (this.cpf == null || this.cpf.isBlank()) {

            mensagensDeErro.add("CPF deve ser informado!");
        } else if (this.cpf.length() != 11) {

            mensagensDeErro.add("CPF Inválido");
        }
        if (!mensagensDeErro.isEmpty()) {

            throw  new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
