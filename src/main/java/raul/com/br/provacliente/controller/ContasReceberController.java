package raul.com.br.provacliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raul.com.br.provacliente.model.Cliente;
import raul.com.br.provacliente.model.ContasReceber;
import raul.com.br.provacliente.repository.ClienteRepository;
import raul.com.br.provacliente.repository.ContasReceberRepository;
import raul.com.br.provacliente.repository.filter.ContasReceberFilter;
import raul.com.br.provacliente.repository.projections.ContasReceberDTO;

import java.util.List;

@RestController
@RequestMapping("/contas-receber")
public class ContasReceberController {

    @Autowired
    private ContasReceberRepository contasReceberRepository;

    @GetMapping("/todos")
    private List<ContasReceber> listarTodosOsClientes()
    {
        return contasReceberRepository.findAll();
    }

    @GetMapping()
    private Page<ContasReceberDTO> filtrar(ContasReceberFilter contasReceberFilter, Pageable pageable)
    {
        return contasReceberRepository.filtrar(contasReceberFilter, pageable);
    }

}
