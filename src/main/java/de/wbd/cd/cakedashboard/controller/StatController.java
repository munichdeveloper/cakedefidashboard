package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.repo.LMRewardPr;
import de.wbd.cd.cakedashboard.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stat")
@Profile("!h2")
public class StatController {
    @Autowired
    private StatService statService;

    @GetMapping("/lmrewardspda")
    public List<LMRewardPr> getLMRewardsPerDayAndAsset() {
        return statService.getLMRewardsPerDayAndAsset();
    }

}
