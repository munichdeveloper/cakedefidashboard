package de.wbd.cd.cakedashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ReportDTO {

    // total liquidity mining reward collected in fiat (as per report stated)
    private BigDecimal totalLMRCollectedFiat;

    // total liquidity mining reward collected per coin (as per report stated)
    private Map<String, Double> totalLMRCollected;
}
