package de.wbd.cd.cakedashboard.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionCSV {

    @CsvBindByPosition(position = 0)
    @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    private LocalDateTime date;

    @CsvBindByPosition(position = 1)
    private String operation;

    @CsvBindByPosition(position = 2)
    private BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "Coin/Asset")
    private String coinAsset;

    @CsvBindByPosition(position = 4)
    private BigDecimal fiatValue;

    @CsvBindByPosition(position = 5)
    private String fiatCurrency;

    @CsvBindByPosition(position = 6)
    private String idTransaction;

    @CsvBindByPosition(position = 7)
    private String withdrawalAddress;

    @CsvBindByPosition(position = 8)
    private String reference;

    @CsvBindByPosition(position = 9)
    private String idRelatedReference;
}
