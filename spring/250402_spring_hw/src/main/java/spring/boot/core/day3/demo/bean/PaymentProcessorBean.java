package spring.boot.core.day3.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentProcessorBean {
    public void processPayment(String string) {
        // System.out.println(string);
        log.info(string);
    }
}
