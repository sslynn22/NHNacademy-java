package com.example.demo.price.service;

import com.example.demo.common.dataparser.DataParser;
import com.example.demo.price.dto.Price;
import com.example.demo.price.formatter.OutPutFormatter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceService {

    @Autowired
    private DataParser dataParser;

    @Autowired
    @Setter
    private OutPutFormatter outPutFormatter;

    public List<String> cities() {
        return dataParser.cities();
    }

    public List<String> sectors(String city) {
        return dataParser.sectors(city);
    }

    public Price price(String city, String sector) {
        return dataParser.price(city, sector);
    }

    public String billTotal(String city, String sector, int usage) {
        return outPutFormatter.format(dataParser.price(city, sector), usage);
    }
}
