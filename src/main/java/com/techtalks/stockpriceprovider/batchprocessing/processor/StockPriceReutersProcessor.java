package com.techtalks.stockpriceprovider.batchprocessing.processor;

import com.techtalks.stockpriceprovider.batchprocessing.mappers.StockPriceBloomBergIDCToStockPriceMapper;
import com.techtalks.stockpriceprovider.batchprocessing.mappers.StockPriceReutersToStockPriceMapper;
import com.techtalks.stockpriceprovider.domain.StockPrice;
import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import com.techtalks.stockpriceprovider.domain.StockPriceReuters;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StockPriceReutersProcessor implements ItemProcessor<StockPriceReuters, StockPrice> {

    @Autowired
    StockPriceReutersToStockPriceMapper stockPriceReutersToStockPriceMapper;

    @Override
    public StockPrice process(StockPriceReuters stockPriceReuters) throws Exception {

        return stockPriceReutersToStockPriceMapper.map(stockPriceReuters);
    }
}
