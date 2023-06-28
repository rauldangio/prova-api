package raul.com.br.provacliente.repository.filter.cliente;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import raul.com.br.provacliente.model.Cliente;
import raul.com.br.provacliente.repository.filter.ClienteFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Cliente> filtrarCliente(ClienteFilter clienteFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        Predicate[] predicates = criarFiltros(root, criteriaBuilder, clienteFilter);
        criteriaQuery.where(predicates);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nomeCliente")));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        adicionarRestricoesDePaginacao(typedQuery, pageable);


        return new PageImpl<>(typedQuery.getResultList(), pageable, total(clienteFilter));
    }

    private Long total(ClienteFilter clienteFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        Predicate[] predicates = criarFiltros(root, criteriaBuilder, clienteFilter);
        criteriaQuery.where(predicates);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nomeCliente")));
        criteriaQuery.select(criteriaBuilder.count(root));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Cliente> typedQuery, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int registrosNaPagina = paginaAtual * totalRegistroPorPagina;
    }

    private Predicate[] criarFiltros(Root<Cliente> root, CriteriaBuilder criteriaBuilder, ClienteFilter clienteFilter) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(clienteFilter.getNomeCliente()))
        {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeCliente")),
                    "%" + clienteFilter.getNomeCliente().toLowerCase() + "%"
                    ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
