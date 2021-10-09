package de.wbd.cd.cakedashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class LMRewardsSummary {

    BigDecimal sum_fiat;

    BigDecimal sum_amount;

    long days;

    BigDecimal fiat_lm_reward_per_day_fiat;

}
