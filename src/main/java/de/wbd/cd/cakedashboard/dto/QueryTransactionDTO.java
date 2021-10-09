package de.wbd.cd.cakedashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryTransactionDTO {
    private List<TransactionDTO> transactions;
    private long total;
}
