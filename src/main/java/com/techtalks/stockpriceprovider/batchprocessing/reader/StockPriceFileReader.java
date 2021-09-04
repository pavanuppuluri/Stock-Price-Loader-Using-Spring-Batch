package com.techtalks.stockpriceprovider.batchprocessing.reader;

import com.techtalks.stockpriceprovider.domain.StockPriceBloomBergIDC;
import com.techtalks.stockpriceprovider.domain.StockPriceReuters;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StockPriceFileReader {

    @Value("${price.file1}")
    private String file1;

    @Value("${price.file2}")
    private String file2;

    @Value("${price.file3}")
    private String file3;

    @Bean
    public FlatFileItemReader reader() {


        FlatFileItemReader stockPriceFileReader= new FlatFileItemReaderBuilder()
                .name("stockPriceItemReader")
                .resource(new ClassPathResource(file3))
                .delimited()
                .delimiter("|")
                .names(new String[] { "Vendor", "Price", "Currency", "StockTicker" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(StockPriceReuters.class);
                }})
                .build();


        stockPriceFileReader.setLinesToSkip(1);
        return stockPriceFileReader;

    }

    @Bean
    public MultiResourceItemReader multiFilesReader() {

        Resource[] multipleFilesResource = new Resource[]{
                new ClassPathResource(file1),
                new ClassPathResource(file2)
        };


        MultiResourceItemReader multiResourceItemReader=new MultiResourceItemReader();
        multiResourceItemReader.setResources(multipleFilesResource);
        multiResourceItemReader.setName("stockPriceItemReaderFromMultipleFiles");
        multiResourceItemReader.setDelegate(getFlatFileItemReaderToDelegate());
        return multiResourceItemReader;
    }

    FlatFileItemReader getFlatFileItemReaderToDelegate()
    {
       FlatFileItemReader fileItemReader= new FlatFileItemReaderBuilder()
                .name("stockPriceItemReaderForMulti")
                .delimited()
                .names(new String[] { "StockSymbol", "Vendor", "Price", "Currency" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(StockPriceBloomBergIDC.class);
                }})
                .build();
       fileItemReader.setLinesToSkip(1);
       return fileItemReader;
    }


}
