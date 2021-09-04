package com.techtalks.stockpriceprovider.batchprocessing.mappers;

import com.techtalks.stockpriceprovider.domain.StockPrice;
import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StockPriceBloomBergIDCToStockPriceMapper {



    public StockPrice map(StockPriceBloomBergIDC stockPriceBloomBergIDC)
    {
        return StockPrice.builder().stockSymbol(stockPriceBloomBergIDC.getStockSymbol())
                .vendor(stockPriceBloomBergIDC.getVendor())
                .price(stockPriceBloomBergIDC.getPrice())
                .currency(stockPriceBloomBergIDC.getCurrency())
                .createdBy("SpringBatchLoader")
                .creationDate(new Date())
                .build();
    }
}
