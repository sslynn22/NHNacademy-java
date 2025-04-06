package spring.boot.core.day1.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class OrderReceiverBean {
    public void receiveOrder() {
        System.out.println("주문을 받는다");
    }
}
