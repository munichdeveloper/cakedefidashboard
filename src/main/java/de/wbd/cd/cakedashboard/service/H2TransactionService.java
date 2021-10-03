package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.entity.Transaction;
import de.wbd.cd.cakedashboard.repo.H2TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("h2")
public class H2TransactionService {

    @Autowired
    private H2TransactionRepository transactionRepository;

    public void persistTransaction(Transaction transaction) {
        Optional<Transaction> byReference = transactionRepository.findByReference(transaction.getReference());
        if (byReference.isEmpty()) {
            transactionRepository.save(transaction);
        }
    }
}
