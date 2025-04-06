package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.MappingIterator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "file.type", havingValue = "csv", matchIfMissing = true)
public class CsvDataParser implements DataParser {

    private final FileProperties fileProperties;
    private final CsvMapper csvMapper;
    private final CsvSchema priceSchema;
    private final CsvSchema accountSchema;

    public CsvDataParser(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
        this.csvMapper = new CsvMapper();
        this.priceSchema = CsvSchema.emptySchema().withHeader();
        this.accountSchema = CsvSchema.emptySchema().withHeader();
    }

    private List<Price> readPrices() {
        try {
            String path = fileProperties.getPricePath();
            InputStream inputStream = new ClassPathResource(path).getInputStream();

            MappingIterator<Price> it = csvMapper
                    .readerFor(Price.class)
                    .with(priceSchema)
                    .readValues(inputStream);

            return it.readAll().stream()
                    .filter(price -> price.getCity() != null && price.getSector() != null)
                    .peek(price -> {
                        price.setCity(price.getCity().trim());
                        price.setSector(price.getSector().trim());
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Account> readAccounts() {
        try {
            String path = fileProperties.getAccountPath();
            InputStream inputStream = new ClassPathResource(path).getInputStream();

            MappingIterator<Account> it = csvMapper
                    .readerFor(Account.class)
                    .with(accountSchema)
                    .readValues(inputStream);

            return it.readAll().stream()
                    .peek(account -> {
                        if (account.getName() != null) account.setName(account.getName().trim());
                        if (account.getPassword() != null) account.setPassword(account.getPassword().trim());
                    })
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> cities() {
        return readPrices().stream()
                .map(Price::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> sectors(String city) {
        return readPrices().stream()
                .filter(entry -> entry.getCity().equalsIgnoreCase(city.trim()))
                .map(Price::getSector)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Price price(String city, String sector) {
        return readPrices().stream()
                .filter(entry -> entry.getCity().equalsIgnoreCase(city.trim())
                        && entry.getSector().equalsIgnoreCase(sector.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> accounts() {
        return readAccounts();
    }
}