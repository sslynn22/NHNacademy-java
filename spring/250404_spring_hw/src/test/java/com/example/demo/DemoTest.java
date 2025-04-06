package com.example.demo;

import com.example.demo.common.dataparser.DataParser;
import com.example.demo.price.dto.Price;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Component
public class DemoTest {
    @Mock
    private DataParser dataParser;

    @Spy
    private KoreanOutputFormatter formatter = new KoreanOutputFormatter();

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void price() {
        when(dataParser.price("대전시", "기숙사용")).thenReturn(null);
        Price price = priceService.price("대전시", "기숙사용");
        assertNull(price);
    }

    @Test
    void billTotal() {
        Price mockPrice = new Price();
        mockPrice.setCity("대전시");
        mockPrice.setSector("기숙사용");
        mockPrice.setUnitPrice(200);

        when(dataParser.price("대전시", "기숙사용")).thenReturn(mockPrice);
        priceService.billTotal("대전시", "기숙사용", 10);
        verify(formatter).format(mockPrice, 10);
    }
}
