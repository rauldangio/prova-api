package raul.com.br.provacliente.repository.projections;

import java.math.BigDecimal;
import java.util.Date;

public class ContasReceberDTO {

    private int id;

    private Date dataConta;

    private String nomeCliente;

    private BigDecimal valorConta;

    public ContasReceberDTO(int id, Date dataConta, String nomeCliente, BigDecimal valorConta) {
        this.id = id;
        this.dataConta = dataConta;
        this.nomeCliente = nomeCliente;
        this.valorConta = valorConta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataConta() {
        return dataConta;
    }

    public void setDataConta(Date dataConta) {
        this.dataConta = dataConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }
}
