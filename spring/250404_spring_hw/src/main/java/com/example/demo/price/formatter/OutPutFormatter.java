package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;


public interface OutPutFormatter {
    String format(Price price, int usage);
}
