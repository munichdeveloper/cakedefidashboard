package de.wbd.cd.cakedashboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

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
