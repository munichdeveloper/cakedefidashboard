package de.wbd.cd.cakedashboard.repo;

import de.wbd.cd.cakedashboard.entity.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("!h2")
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    Optional<Transaction> findByReference(String reference);

    @Query(value = "select sum(fiat_value)               sum_fiat,\n" +
            "       sum(amount) sum_amount,\n" +
            "       coin_asset,\n" +
            "       STR_TO_DATE(date_format(date, '%d.%m.%Y'), '%d.%m.%Y') date\n" +
            "from transaction\n" +
            "where operation like 'Liquidity mining reward%'\n" +
            "and date > ?1 and date < ?2 + interval 1 day \n" +
            "group by coin_asset, date_format(date, '%d.%m.%Y')\n" +
            "order by STR_TO_DATE(date_format(date, '%d.%m.%Y'), '%d.%m.%Y'), coin_asset desc", nativeQuery = true)
    List<LMRewardPr> getLMRewardsPerDayAndAsset(LocalDate from, LocalDate to);

    @Query(value = "select sum_fiat, sum_amount, days, sum_fiat/days fiat_lm_reward_per_day_fiat from (\n" +
            "select sum(fiat_value) sum_fiat, sum(amount) sum_amount,count(distinct(date_format(date, '%d.%m.%Y'))) days\n" +
            "from transaction\n" +
            "where operation like 'Liquidity mining reward%') d", nativeQuery = true)
    LMRewardsSummaryPrj getLMRewardsSummary();

    Page<Transaction> findAll(Pageable pageable);

    @Query(value = "select distinct(operation) from transaction", nativeQuery = true)
    ArrayList<String> findDistinctOperations();

    @Query(value = "select distinct(coin_asset) from transaction", nativeQuery = true)
    ArrayList<String> findDistinctAssets();
}
