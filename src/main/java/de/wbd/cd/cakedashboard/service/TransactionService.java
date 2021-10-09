package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.dto.QueryTransactionDTO;
import de.wbd.cd.cakedashboard.dto.TransactionDTO;
import de.wbd.cd.cakedashboard.entity.Transaction;
import de.wbd.cd.cakedashboard.repo.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;

    public void persistTransaction(Transaction transaction) {
        Optional<Transaction> byReference = transactionRepository.findByReference(transaction.getReference());
        if (byReference.isEmpty()) {
            transactionRepository.save(transaction);
        }
    }

//    public LMRewardsSummary getLMRewardsSummary() {
//        return modelMapper.map(transactionRepository.getLMRewardsSummary(), LMRewardsSummary.class);
//    }

    public QueryTransactionDTO queryTransactions(int page, int size, String operation, String asset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();

        if (operation != null) {
            Predicate predicate = cb.equal(root.get("operation"), operation);
            predicates.add(predicate);
        }

        if (asset != null) {
            Predicate predicate = cb.equal(root.get("coinAsset"), asset);
            predicates.add(predicate);
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        cq.orderBy(cb.desc(root.get("date")));

        TypedQuery<Transaction> query = entityManager.createQuery(cq);
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        List<Transaction> resultList = query.getResultList();

        long count = getCountForQueryTransactions(operation, asset);

        QueryTransactionDTO queryTransactionDTO = new QueryTransactionDTO();
        queryTransactionDTO.setTransactions(resultList.stream().map(this::map).collect(Collectors.toList()));
        queryTransactionDTO.setTotal(count);
        return queryTransactionDTO;
    }

    private TransactionDTO map(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    private long getCountForQueryTransactions(String operation, String asset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Transaction> root = cq.from(Transaction.class);

        List<Predicate> predicates = new ArrayList<>();

        if (operation != null) {
            Predicate predicate = cb.equal(root.get("operation"), operation);
            predicates.add(predicate);
        }

        if (asset != null) {
            Predicate predicate = cb.equal(root.get("coinAsset"), asset);
            predicates.add(predicate);
        }

        cq.select(cb.count(root));
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    public ArrayList<String> getDistinctOperations() {
        return transactionRepository.findDistinctOperations();
    }

    public ArrayList<String> getDistinctAssets() {
        return transactionRepository.findDistinctAssets();
    }
}
