package de.wbd.cd.cakedashboard.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Ignore
public class ReportProcessorTest {

    @Autowired
    private ReportProcessor reportProcessor;

    @Test
    public void processReport() throws Exception {
        reportProcessor.processReport();
    }
}
