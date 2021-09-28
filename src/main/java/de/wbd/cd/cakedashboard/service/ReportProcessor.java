package de.wbd.cd.cakedashboard.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.wbd.cd.cakedashboard.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class ReportProcessor {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<Transaction> processReport() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:report.csv");
        File file = resource.getFile();
        CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder(new FileReader(file))
                .withType(Transaction.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();
       return csvToBean.parse();
    }


}
