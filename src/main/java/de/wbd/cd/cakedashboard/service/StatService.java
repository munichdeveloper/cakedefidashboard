package de.wbd.cd.cakedashboard.service;

import de.wbd.cd.cakedashboard.repo.LMRewardPr;
import de.wbd.cd.cakedashboard.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("!h2")
public class StatService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<LMRewardPr> getLMRewardsPerDayAndAsset(LocalDate from, LocalDate to) {
        return transactionRepository.getLMRewardsPerDayAndAsset(from, to);
    }

}
