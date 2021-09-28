package de.wbd.cd.cakedashboard.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportProcessorTest {

    @InjectMocks
    private ReportProcessor reportProcessor;

    @Test
    public void processReport() throws Exception {
        reportProcessor.processReport();
    }
}
