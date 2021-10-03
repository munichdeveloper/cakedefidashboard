package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.repo.H2LMRewardPr;
import de.wbd.cd.cakedashboard.repo.H2TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("h2")
public class H2StatService {

    @Autowired
    private H2TransactionRepository transactionRepository;

    public List<H2LMRewardPr> getLMRewardsPerDayAndAsset() {
        return transactionRepository.getLMRewardsPerDayAndAsset();
    }

}
