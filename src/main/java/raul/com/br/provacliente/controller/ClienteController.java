package raul.com.br.provacliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raul.com.br.provacliente.model.Cliente;
import raul.com.br.provacliente.repository.ClienteRepository;
import raul.com.br.provacliente.repository.filter.ClienteFilter;
import raul.com.br.provacliente.repository.filter.ContasReceberFilter;
import raul.com.br.provacliente.repository.projections.ContasReceberDTO;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/todos")
    private List<Cliente> listarTodosOsClientes()
    {
        return clienteRepository.findAll();
    }

    @GetMapping()
    private Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable)
    {
        return clienteRepository.filtrarCliente(clienteFilter, pageable);
    }
}
