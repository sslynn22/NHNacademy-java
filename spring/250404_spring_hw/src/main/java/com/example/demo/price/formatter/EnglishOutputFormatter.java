package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("eng")
public class EnglishOutputFormatter implements OutPutFormatter {
    @Override
    public String format(Price price, int usage) {
        return String.format("city: %s, sector: %s, unit price: %s, bill total(won): %s",
                price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice() * usage);
    }
}
