import java.util.ArrayList;
import java.util.List;

public class Endereco {

    private String rua;
    private String numero = null;
    private String bairro;
    private String complemento = null;
    private String cep;
    private String cidade;
    private String estado;

    public Endereco(String rua, String bairro, String cep, String cidade, String estado) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        validar();
    }

    public Endereco(String rua, String numero, String bairro, String complemento, String cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        validar();
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    protected List<String> validar() {

        List<String> mensagensDeErro = new ArrayList<>();

        if (this.rua == null || this.rua.isBlank()) {

            mensagensDeErro.add("Rua deve ser informada!");
        } else if (this.rua.length() > 100) {

            mensagensDeErro.add("Número de caracteres de rua excedido!");
        }
        if (this.bairro == null || this.bairro.isBlank()) {

            mensagensDeErro.add("Bairro deve ser informado!");
        } else if (this.bairro.length() > 50) {

            mensagensDeErro.add("Número de caracteres de bairro excedido!");
        }
        if (this.cep == null || this.cep.isBlank()) {

            mensagensDeErro.add("CEP deve ser informado!");
        } else if (this.cep.length() > 10) {

            mensagensDeErro.add("Número de caracteres de CEP excedido!");
        }
        if (this.cidade == null || this.cidade.isBlank()) {

            mensagensDeErro.add("Cidade deve ser informada!");
        } else if (this.cidade.length() > 50) {

            mensagensDeErro.add("Número de caracteres de cidade excedido!");
        }
        if (this.estado == null || this.estado.isBlank()) {

            mensagensDeErro.add("Estado deve ser informado!");
        } else if (this.estado.length() != 2) {

            mensagensDeErro.add("Número de caracteres de estado inválido!");
        }
        if (this.numero != null && this.numero.length() > 6) {

            mensagensDeErro.add("Número de caracteres de numero excedido!");
        }
        if (this.complemento != null && this.complemento.length() > 120) {

            mensagensDeErro.add("Número de caracteres de complemento excedido!");
        }

        return mensagensDeErro;
    }
}
