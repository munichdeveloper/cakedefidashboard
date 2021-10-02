package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.repo.LMRewardPr;
import de.wbd.cd.cakedashboard.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<LMRewardPr> getLMRewardsPerDayAndAsset() {
        return transactionRepository.getLMRewardsPerDayAndAsset();
    }

}
