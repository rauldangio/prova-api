package raul.com.br.provacliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raul.com.br.provacliente.model.Cliente;
import raul.com.br.provacliente.repository.ClienteRepository;

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
}
