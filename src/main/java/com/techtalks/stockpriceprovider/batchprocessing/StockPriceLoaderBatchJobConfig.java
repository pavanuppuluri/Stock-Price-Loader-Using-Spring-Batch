package com.techtalks.stockpriceprovider.batchprocessing;

import com.techtalks.stockpriceprovider.batchprocessing.configuration.BatchJobCompletedNotifyListener;
import com.techtalks.stockpriceprovider.batchprocessing.mappers.StockPriceReutersToStockPriceMapper;
import com.techtalks.stockpriceprovider.batchprocessing.processor.StockPriceBloomBergIDCProcessor;
import com.techtalks.stockpriceprovider.batchprocessing.processor.StockPriceReutersProcessor;
import com.techtalks.stockpriceprovider.domain.StockPrice;
import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import com.techtalks.stockpriceprovider.domain.StockPriceReuters;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class StockPriceLoaderBatchJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Autowired
    public FlatFileItemReader stockPriceFileReader;

    @Autowired
    MultiResourceItemReader multiResourceItemReader;

    @Autowired
    JdbcBatchItemWriter stockPriceDBWriter;

    @Autowired
    StockPriceBloomBergIDCProcessor stockPriceBloomBergIDCProcessor;

    @Autowired
    StockPriceReutersProcessor stockPriceReutersProcessor;


    @Bean
    public Job loadStockPricesJob(BatchJobCompletedNotifyListener listener, Step step1, Step step2) {
        return jobBuilderFactory.get("loadStockPricesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<StockPriceBloomBergIDC, StockPriceBloomBergIDC> chunk(10)
                .reader(multiResourceItemReader)
                .processor(stockPriceBloomBergIDCProcessor)
                .writer(stockPriceDBWriter)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<StockPriceReuters, StockPrice> chunk(10)
                .reader(stockPriceFileReader)
                .processor(stockPriceReutersProcessor)
                .writer(stockPriceDBWriter)
                .build();
    }


}
