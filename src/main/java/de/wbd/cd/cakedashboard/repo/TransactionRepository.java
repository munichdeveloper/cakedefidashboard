package de.wbd.cd.cakedashboard.repo;

import de.wbd.cd.cakedashboard.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByReference(String reference);

    @Query(value = "select sum(fiat_value)               sum_fiat,\n" +
            "       sum(amount) sum_amount,\n" +
            "       coin_asset,\n" +
            "       STR_TO_DATE(date_format(date, '%d.%m.%Y'), '%d.%m.%Y') date\n" +
            "from transaction\n" +
            "where operation like 'Liquidity mining reward%'\n" +
            "group by coin_asset, date_format(date, '%d.%m.%Y')\n" +
            "order by STR_TO_DATE(date_format(date, '%d.%m.%Y'), '%d.%m.%Y'), coin_asset desc", nativeQuery = true)
    List<LMRewardPr> getLMRewardsPerDayAndAsset();
}
