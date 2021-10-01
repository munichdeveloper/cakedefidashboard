package de.wbd.cd.cakedashboard.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.wbd.cd.cakedashboard.dto.TransactionCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReportProcessor {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<TransactionCSV> processReport() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:report.csv");
        File file = getFile("report.csv");
        CsvToBean<TransactionCSV> csvToBean = new CsvToBeanBuilder(new FileReader(file))
                .withType(TransactionCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();
        return csvToBean.parse();
    }

    private File getFile(String filename) {
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        return new File(absolutePath + "/" + filename);
    }

}
