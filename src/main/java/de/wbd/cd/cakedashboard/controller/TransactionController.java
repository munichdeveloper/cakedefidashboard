package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tx")
@Profile("!h2")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public QueryTransactionDTO queryTransactions(@RequestParam int page, @RequestParam int size,
                                                 @RequestParam(required = false) String operation) {
        return transactionService.queryTransactions(
                page, size, operation
        );
    }

}
