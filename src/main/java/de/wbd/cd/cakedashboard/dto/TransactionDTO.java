package de.wbd.cd.cakedashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private LocalDateTime date;

    private String operation;

    private BigDecimal amount;

    private String coinAsset;

    private BigDecimal fiatValue;

    private String fiatCurrency;

    private String idTransaction;

    private String withdrawalAddress;

    private String reference;

    private String idRelatedReference;

}
