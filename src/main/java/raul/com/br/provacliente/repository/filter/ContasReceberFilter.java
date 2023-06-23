package raul.com.br.provacliente.repository.filter;

import java.util.Date;

public class ContasReceberFilter {

    private String nomeCliente;
    private Date dataConta;

    public ContasReceberFilter(String nomeCliente, Date dataConta) {
        this.nomeCliente = nomeCliente;
        this.dataConta = dataConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataConta() {
        return dataConta;
    }

    public void setDataConta(Date dataConta) {
        this.dataConta = dataConta;
    }
}
