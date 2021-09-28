package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.dto.Transaction;
import de.wbd.cd.cakedashboard.service.ReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportProcessor reportProcessor;

    @GetMapping
    public ReportDTO getReport() throws IOException {

        List<Transaction> transactions = reportProcessor.processReport();
        return mapToReport(transactions);
    }

    private ReportDTO mapToReport(List<Transaction> transactions) {
        ReportDTO reportDTO = new ReportDTO();

        BigDecimal lmrSum = transactions.stream()
                .filter(transaction -> transaction.getOperation().contains("Liquidity mining reward USDT-DFI"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        reportDTO.setTotalLMRCollectedFiat(lmrSum);

        return reportDTO;
    }
}
