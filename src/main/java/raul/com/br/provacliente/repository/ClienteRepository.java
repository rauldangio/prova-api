package raul.com.br.provacliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raul.com.br.provacliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
