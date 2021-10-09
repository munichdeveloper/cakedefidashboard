package de.wbd.cd.cakedashboard.repo;

import de.wbd.cd.cakedashboard.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    Optional<Transaction> findByReference(String reference);

    Page<Transaction> findAll(Pageable pageable);

    @Query(value = "select distinct(operation) from transaction", nativeQuery = true)
    ArrayList<String> findDistinctOperations();

    @Query(value = "select distinct(coin_asset) from transaction", nativeQuery = true)
    ArrayList<String> findDistinctAssets();
}
