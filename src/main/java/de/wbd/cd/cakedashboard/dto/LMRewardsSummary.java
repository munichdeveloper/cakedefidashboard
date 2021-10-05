package de.wbd.cd.cakedashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LMRewardsSummary {

    BigDecimal sum_fiat;

    BigDecimal sum_amount;

    int days;

    BigDecimal fiat_lm_reward_per_day_fiat;

}
