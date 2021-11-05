import java.util.ArrayList;
import java.util.List;

public class Contato {

    private String email;

    private String telefone;

    public Contato(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
        validar();
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void validar() {

        List<String> mensagensDeErro = new ArrayList<>();

        if (this.email == null || this.email.isBlank()) {

            mensagensDeErro.add("Email deve ser informado!");
        } else if (this.email.length() > 100) {

            mensagensDeErro.add("Número de caracteres de email excedido!");
        }
        if (this.telefone == null || this.telefone.isBlank()) {

            mensagensDeErro.add("Telefone deve ser informado!");
        } else if (this.telefone.length() > 20) {

            mensagensDeErro.add("Número de caracteres de telefone excedido!");
        }
        if(!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
