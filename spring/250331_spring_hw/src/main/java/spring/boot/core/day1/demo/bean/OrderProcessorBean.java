package spring.boot.core.day1.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessorBean {
    // 필드 주입 예시
    @Autowired
    private PaymentProcessorBean paymentProcessorBean;
    private OrderReceiverBean orderReceiverBean;

    // setter 주입 예시
    @Autowired
    public void setOrderReceiverBean(OrderReceiverBean orderReceiverBean) {
        this.orderReceiverBean = orderReceiverBean;
    }

    public void processOrder() {
        orderReceiverBean.receiveOrder();
        paymentProcessorBean.processPayment();
    }
}
