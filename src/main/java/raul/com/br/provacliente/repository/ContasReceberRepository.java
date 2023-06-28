package raul.com.br.provacliente.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import raul.com.br.provacliente.model.ContasReceber;
import raul.com.br.provacliente.repository.filter.contasReceber.ContasReceberRepositoryQuery;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Integer>, ContasReceberRepositoryQuery {
}
