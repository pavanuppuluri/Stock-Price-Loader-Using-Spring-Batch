package com.techtalks.stockpriceprovider.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StockPrice {
    String stockSymbol;
    String vendor;
    BigDecimal price;
    String currency;
    String createdBy;
    Date creationDate;
}
