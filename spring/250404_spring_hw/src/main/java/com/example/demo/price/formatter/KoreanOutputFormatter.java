package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!eng")
public class KoreanOutputFormatter implements OutPutFormatter {
    @Override
    public String format(Price price, int usage) {
        return String.format("지자체명: %s, 업종: %s, 구간금액(원): %s, 총금액(원): %s",
                price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice() * usage);
    }
}
