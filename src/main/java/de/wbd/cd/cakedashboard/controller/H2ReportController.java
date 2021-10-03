package de.wbd.cd.cakedashboard.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.wbd.cd.cakedashboard.dto.TransactionCSV;
import de.wbd.cd.cakedashboard.entity.Transaction;
import de.wbd.cd.cakedashboard.service.H2TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
@Profile("h2")
public class H2ReportController {

    @Autowired
    private H2TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/uploadReport")
    public ResponseEntity uploadReport(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("prefix", null, null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(file.getBytes());

        CsvToBean<TransactionCSV> csvToBean = new CsvToBeanBuilder(new FileReader(tempFile))
                .withType(TransactionCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();
        List<TransactionCSV> transactions = csvToBean.parse();
        transactions.stream().map(this::mapTransaction).forEach(transaction -> transactionService.persistTransaction(transaction));
        return ResponseEntity.ok().build();
    }

    private Transaction mapTransaction(TransactionCSV transactionCSV) {
        return this.modelMapper.map(transactionCSV, Transaction.class);
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
                .collect(Collectors.groupingBy(transaction -> transaction.getOperation().replace("Liquidity mining reward", "").trim(),
                        Collectors.mapping(TransactionCSV::getAmount, Collectors.summingDouble(BigDecimal::doubleValue))
                ));
        reportDTO.setTotalLMRCollected(liquidity_mining_reward);

        return reportDTO;
    }
}
