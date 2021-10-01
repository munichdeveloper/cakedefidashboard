package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.dto.TransactionCSV;
import de.wbd.cd.cakedashboard.entity.Transaction;
import de.wbd.cd.cakedashboard.service.ReportProcessor;
import de.wbd.cd.cakedashboard.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportProcessor reportProcessor;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public void processReport() throws IOException {
        List<TransactionCSV> transactions = reportProcessor.processReport();
        transactions.stream().map(this::mapTransaction).forEach(transaction -> transactionService.persistTransaction(transaction));
    }

    private Transaction mapTransaction(TransactionCSV transactionCSV) {
        return this.modelMapper.map(transactionCSV, Transaction.class);
    }

    @GetMapping
    public ReportDTO getReport() throws IOException {
        List<TransactionCSV> transactions = reportProcessor.processReport();
        return mapToReport(transactions);
    }

    private ReportDTO mapToReport(List<TransactionCSV> transactions) {
        ReportDTO reportDTO = new ReportDTO();

        BigDecimal lmrSum = transactions.stream()
                .filter(transaction -> transaction.getOperation().contains("Liquidity mining reward"))
                .map(TransactionCSV::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        reportDTO.setTotalLMRCollectedFiat(lmrSum);

        Map<String, Double> liquidity_mining_reward = transactions.stream()
                .filter(transaction -> transaction.getOperation().contains("Liquidity mining reward"))
                .collect(Collectors.groupingBy(transaction -> transaction.getOperation().replace("Liquidity mining reward","").trim(),
                        Collectors.mapping(TransactionCSV::getAmount, Collectors.summingDouble(BigDecimal::doubleValue))
                ));
        reportDTO.setTotalLMRCollected(liquidity_mining_reward);

        return reportDTO;
    }
}
