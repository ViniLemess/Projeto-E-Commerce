public class Fornecedor extends Pessoa {

    private String nomeFantasia;
    private String cnpj;

    public Fornecedor(Contato contato, Endereco endereco, String nomeFantasia, String cnpj) {
        super(contato, endereco, 0);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        validar();
    }
    public Fornecedor(int Id,Contato contato, Endereco endereco, String nomeFantasia, String cnpj) {
        super(contato, endereco, Id);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        validar();
    }


    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    protected void validar() {

        if (this.nomeFantasia == null || this.nomeFantasia.isBlank()) {

            mensagensDeErro.add("Nome Fantasia deve ser informado!");
        } else if (this.nomeFantasia.length() > 100) {

            mensagensDeErro.add("Número de caracteres de nome fantasia excedido!");
        }
        if (this.cnpj == null || this.cnpj.isBlank()) {

            mensagensDeErro.add("CNPJ deve ser informado!");
        } else if (this.cnpj.length() != 14) {

            mensagensDeErro.add("CNPJ Inválido");
        }
        if (!mensagensDeErro.isEmpty()) {

            throw new IllegalArgumentException(mensagensDeErro.toString());
        }
    }
}
