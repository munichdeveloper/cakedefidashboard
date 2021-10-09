package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.service.pricedata.TickerService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
class TickerServiceTest {

    @Autowired
    private TickerService tickerService;

    @Test
    public void fetchPrices() {
        tickerService.fetchPrices();
    }

    @Test
    public void testCron() {
        var expression = CronExpression.parse("0 0 */1 * * ?");
        var result = expression.next(LocalDateTime.now());
        System.out.println(result);
    }
}
