package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.dto.LMRewardDTO;
import de.wbd.cd.cakedashboard.dto.LMRewardsSummary;
import de.wbd.cd.cakedashboard.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {

    @Autowired
    private EntityManager entityManager;

    public List<LMRewardDTO> getLMRewardsPerDayAndAsset(LocalDate from, LocalDate to) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LMRewardDTO> cq = cb.createQuery(LMRewardDTO.class);
        Root<Transaction> root = cq.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();

        if (from != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(root.get("date"), LocalDateTime.of(from, LocalTime.MIN));
            predicates.add(predicate);
        }

        if (to != null) {
            Predicate predicate = cb.lessThanOrEqualTo(root.get("date"), LocalDateTime.of(to, LocalTime.MIN));
            predicates.add(predicate);
        }

        Predicate predicate = cb.like(root.get("operation"), "Liquidity mining reward%");
        predicates.add(predicate);

        cq.multiselect(
                cb.sum(root.get("fiatValue")),
                cb.sum(root.get("amount")),
                root.get("coinAsset"),
                root.get("date").as(LocalDate.class)
        );

        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        cq.groupBy(root.get("coinAsset"), root.get("date").as(LocalDate.class));

        TypedQuery<LMRewardDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    public LMRewardsSummary getLMRewardsSummary() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LMRewardsSummary> cq = cb.createQuery(LMRewardsSummary.class);
        Root<Transaction> root = cq.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate predicate = cb.like(root.get("operation"), "Liquidity mining reward%");
        predicates.add(predicate);

        cq.multiselect(
                cb.sum(root.get("fiatValue")),
                cb.sum(root.get("amount")),
                cb.countDistinct(root.get("date").as(LocalDate.class)),
                cb.quot(cb.sum(root.get("fiatValue")), cb.countDistinct(root.get("date").as(LocalDate.class)))
        );

        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<LMRewardsSummary> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }


}
