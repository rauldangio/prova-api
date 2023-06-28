package raul.com.br.provacliente.repository.filter.contasReceber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raul.com.br.provacliente.repository.filter.ContasReceberFilter;
import raul.com.br.provacliente.repository.projections.ContasReceberDTO;

public interface ContasReceberRepositoryQuery {
    public Page<ContasReceberDTO> filtrar(ContasReceberFilter contasReceberFilter, Pageable pageable);
}
