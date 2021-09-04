package com.techtalks.stockpriceprovider.batchprocessing.mappers;

import com.techtalks.stockpriceprovider.domain.StockPrice;
import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import com.techtalks.stockpriceprovider.domain.StockPriceReuters;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StockPriceReutersToStockPriceMapper {



    public StockPrice map(StockPriceReuters stockPriceReuters)
    {
        return StockPrice.builder().stockSymbol(stockPriceReuters.getStockTicker())
                .vendor(stockPriceReuters.getVendor())
                .price(stockPriceReuters.getPrice())
                .currency(stockPriceReuters.getCurrency())
                .createdBy("SpringBatchLoader")
                .creationDate(new Date())
                .build();
    }
}
