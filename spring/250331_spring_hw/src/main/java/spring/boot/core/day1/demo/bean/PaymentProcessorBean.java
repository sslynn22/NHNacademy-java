package spring.boot.core.day1.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class PaymentProcessorBean {
    public void processPayment() {
        System.out.println("결제 처리를 한다");
    }
}
