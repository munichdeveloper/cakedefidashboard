package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.dto.QueryTransactionDTO;
import de.wbd.cd.cakedashboard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/tx")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public QueryTransactionDTO queryTransactions(@RequestParam int page, @RequestParam int size,
                                                 @RequestParam(required = false) String operation,
                                                 @RequestParam(required = false) String asset) {
        return transactionService.queryTransactions(
                page, size, operation, asset
        );
    }

    @GetMapping("/enumeration/operations")
    public ArrayList<String> getDistinctOperations() {
        return transactionService.getDistinctOperations();
    }

    @GetMapping("/enumeration/assets")
    public ArrayList<String> getDistinctAssets() {
        return transactionService.getDistinctAssets();
    }

}
