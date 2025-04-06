package com.example.demo;

import com.example.demo.price.formatter.EnglishOutputFormatter;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
@ActiveProfiles("eng")
public class EngProfileTest {

    @Autowired
    private OutPutFormatter outPutFormatter;

    @Test
    void beanTest() {
        assertInstanceOf(EnglishOutputFormatter.class, outPutFormatter);
    }


}
