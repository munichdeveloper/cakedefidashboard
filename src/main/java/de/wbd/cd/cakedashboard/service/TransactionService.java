package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.entity.Transaction;
import de.wbd.cd.cakedashboard.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void persistTransaction(Transaction transaction) {
        Optional<Transaction> byReference = transactionRepository.findByReference(transaction.getReference());
        if (byReference.isEmpty()) {
            transactionRepository.save(transaction);
        }
    }
}
