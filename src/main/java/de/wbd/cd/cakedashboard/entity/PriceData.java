package de.wbd.cd.cakedashboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PriceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceDataId;

    private String symbol;

    private String name;

    private String price;

    private String priceDate;

    private String priceTimestamp;

    private String rank;

    private String rankDelta;

    private String marketCap;

    private String marketCapDominance;

}
