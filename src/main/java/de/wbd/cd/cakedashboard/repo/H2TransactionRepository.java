package de.wbd.cd.cakedashboard.repo;

import de.wbd.cd.cakedashboard.entity.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("h2")
public interface H2TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByReference(String reference);

    @Query(value = "select                    \n" +
            " sum(fiat_value)               sum_fiat,\n" +
            "                   sum(amount) sum_amount,\n" +
            "coin_asset,\n" +
            "                   formatdatetime(date, 'dd.MM.YY') date\n" +
            "            from transaction\n" +
            "            where operation like 'Liquidity mining reward%'\n" +
            "  group by coin_asset, formatdatetime(date, 'dd.MM.YY')\n" +
            "  order by formatdatetime(date, 'dd.MM.YY'), coin_asset desc\n", nativeQuery = true)
    List<H2LMRewardPr> getLMRewardsPerDayAndAsset();
}
