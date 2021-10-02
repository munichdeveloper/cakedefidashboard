package de.wbd.cd.cakedashboard.repo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LMRewardPr {

    BigDecimal getSum_amount();

    String getCoin_asset();

    LocalDate getDate();

    BigDecimal getSum_fiat();
}
