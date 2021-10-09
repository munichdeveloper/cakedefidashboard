package de.wbd.cd.cakedashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LMRewardDTO {

    BigDecimal sum_fiat;

    BigDecimal sum_amount;

    String coin_asset;

    LocalDate date;

}
