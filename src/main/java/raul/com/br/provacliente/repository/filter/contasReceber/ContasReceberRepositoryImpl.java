package raul.com.br.provacliente.repository.filter.contasReceber;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import raul.com.br.provacliente.model.ContasReceber;
import raul.com.br.provacliente.repository.filter.ContasReceberFilter;
import raul.com.br.provacliente.repository.projections.ContasReceberDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContasReceberRepositoryImpl implements ContasReceberRepositoryQuery{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContasReceberDTO> filtrar(ContasReceberFilter contasReceberFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContasReceberDTO> criteria = builder.createQuery(ContasReceberDTO.class);
        Root<ContasReceber> root = criteria.from(ContasReceber.class);

        criteria.select(builder.construct(ContasReceberDTO.class,
                root.get("id"),
                root.get("dataConta"),
                root.get("cliente").get("nomeCliente"),
                root.get("valorConta")
        ));


        Predicate[] predicates = criarRestricoes(builder, root, contasReceberFilter);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("cliente").get("nomeCliente")));

        TypedQuery<ContasReceberDTO> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(contasReceberFilter));

    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaatual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(ContasReceberFilter contasReceberFilter)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ContasReceber> root = criteria.from(ContasReceber.class);

        Predicate[] predicates = criarRestricoes(builder, root, contasReceberFilter);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("cliente").get("nomeCliente")));
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Predicate[] criarRestricoes(CriteriaBuilder builder, Root<ContasReceber> root, ContasReceberFilter contasReceberFilter) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(contasReceberFilter.getNomeCliente())){
            predicates.add(builder.like(builder.lower(root.get("cliente").get("nomeCliente")),
                    "%" + contasReceberFilter.getNomeCliente().toLowerCase() + "%"));
        }

        // datas maiores que a inserida
        if (contasReceberFilter.getDataConta() != null)
        {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataConta"),
                     contasReceberFilter.getDataConta()
            ));
        }

        // data menores que a sua
        if (contasReceberFilter.getDataConta() != null)
        {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataConta"),
                     contasReceberFilter.getDataConta()
            ));
        }





        return predicates.toArray(new Predicate[predicates.size()]);

    }
}

