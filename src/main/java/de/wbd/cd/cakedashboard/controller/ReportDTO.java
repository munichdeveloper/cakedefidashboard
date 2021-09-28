package de.wbd.cd.cakedashboard.controller;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Getter
@Setter
public class ReportDTO {

    // total liquidity mining reward collected in fiat (as per report stated)
    private BigDecimal totalLMRCollectedFiat;

    // total liquidity mining reward collected per coin (as per report stated)
    private HashMap<String, BigDecimal> totalLMRCollected;
}
