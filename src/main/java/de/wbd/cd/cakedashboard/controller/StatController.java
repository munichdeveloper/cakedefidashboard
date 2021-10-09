package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.dto.LMRewardDTO;
import de.wbd.cd.cakedashboard.dto.LMRewardsSummary;
import de.wbd.cd.cakedashboard.service.StatService;
import de.wbd.cd.cakedashboard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private StatService statService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/lmrewardspda/{from}/{to}")
    public List<LMRewardDTO> getLMRewardsPerDayAndAsset(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return statService.getLMRewardsPerDayAndAsset(from, to);
    }

    @GetMapping("/lmrewardssum")
    public LMRewardsSummary getLMRewardsSummary() {
        return statService.getLMRewardsSummary();
    }
}
