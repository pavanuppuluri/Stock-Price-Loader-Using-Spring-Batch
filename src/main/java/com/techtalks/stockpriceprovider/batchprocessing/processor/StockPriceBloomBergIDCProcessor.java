package com.techtalks.stockpriceprovider.batchprocessing.processor;

import com.techtalks.stockpriceprovider.batchprocessing.mappers.StockPriceBloomBergIDCToStockPriceMapper;
import com.techtalks.stockpriceprovider.domain.StockPrice;
import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class StockPriceBloomBergIDCProcessor implements ItemProcessor<StockPriceBloomBergIDC, StockPrice> {

    @Autowired
    StockPriceBloomBergIDCToStockPriceMapper stockPriceBloomBergIDCToStockPriceMapper;

    @Override
    public StockPrice process(StockPriceBloomBergIDC stockPriceBloomBergIDC) throws Exception {

        return stockPriceBloomBergIDCToStockPriceMapper.map(stockPriceBloomBergIDC);
    }
}
