package spring.boot.core.day2.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class OrderReceiverBean {
    public void receiveOrder(String string) {
        System.out.println(string);
    }
}
