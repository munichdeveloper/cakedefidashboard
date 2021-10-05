package de.wbd.cd.cakedashboard.repo;

import java.math.BigDecimal;

public interface LMRewardsSummaryPrj {

    BigDecimal getSum_fiat();

    BigDecimal getSum_amount();

    int getDays();

    BigDecimal getFiat_lm_reward_per_day_fiat();

}
