package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "file.type", havingValue="json")
public class JsonDataParser implements DataParser {

    private final FileProperties fileProperties;
    private final ObjectMapper objectMapper;

    public JsonDataParser(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private List<Price> readPrices() {
        try {
            String path = fileProperties.getPricePath();
            InputStream inputStream = new ClassPathResource(path).getInputStream();

            return objectMapper.readValue(inputStream, new TypeReference<List<Price>>() {})
                    .stream()
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
            return objectMapper.readValue(inputStream, new TypeReference<List<Account>>() {});
        } catch (Exception e) {
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