package de.wbd.cd.cakedashboard.controller;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LMRewardDTO {

    BigDecimal sum_amount;

    String coin_asset;

    LocalDate date;

    BigDecimal sum_fiat;
}
