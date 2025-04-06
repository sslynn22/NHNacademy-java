package spring.boot.core.day2.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class PaymentProcessorBean {
    public void processPayment(String string) {
        System.out.println(string);
    }
}