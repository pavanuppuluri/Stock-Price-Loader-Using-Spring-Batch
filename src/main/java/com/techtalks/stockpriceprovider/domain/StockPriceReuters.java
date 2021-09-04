package com.techtalks.stockpriceprovider.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StockPriceReuters {

    String vendor;
    BigDecimal price;
    String currency;
    String stockTicker;
    String createdBy;
    Date creationDate;
}
