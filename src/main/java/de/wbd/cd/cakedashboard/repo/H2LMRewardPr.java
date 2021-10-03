package de.wbd.cd.cakedashboard.repo;

import java.math.BigDecimal;

public interface H2LMRewardPr {

    BigDecimal getSum_amount();

    String getCoin_asset();

    String getDate();

    BigDecimal getSum_fiat();
}
