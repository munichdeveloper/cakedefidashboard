package de.wbd.cd.cakedashboard.repo;

import java.math.BigDecimal;

public interface LMRewardPr {

    BigDecimal getSum_amount();

    String getCoin_asset();

    Object getDate();

    BigDecimal getSum_fiat();
}
