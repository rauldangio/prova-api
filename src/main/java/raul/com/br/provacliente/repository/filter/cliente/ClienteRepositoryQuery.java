package raul.com.br.provacliente.repository.filter.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raul.com.br.provacliente.model.Cliente;
import raul.com.br.provacliente.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

    public Page<Cliente> filtrarCliente(ClienteFilter clienteFilter, Pageable pageable);

}
