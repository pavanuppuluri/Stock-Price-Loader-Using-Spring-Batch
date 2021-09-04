package com.techtalks.stockpriceprovider.batchprocessing.writer;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StockPriceDBWriter {

    @Bean
    public JdbcBatchItemWriter writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO STOCK_PRICE(STOCK_SYMBOL, VENDOR , PRICE , CURRENCY, CREATED_BY, CREATION_DATE) VALUES (:stockSymbol, :vendor, :price, :currency, :createdBy, :creationDate)")
                .dataSource(dataSource)
                .build();
    }
}
