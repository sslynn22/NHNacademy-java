package spring.boot.core.day3.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import spring.boot.core.day3.demo.properties.CookProperties;

@Component
@ConditionalOnBean({PaymentProcessorBean.class, OrderProcessorBean.class})
public class OrderProcessorBean {
    @Autowired
    private PaymentProcessorBean paymentProcessorBean;
    @Autowired
    private OrderReceiverBean orderReceiverBean;
    @Autowired
    private CookProperties cookProperties;

    public void processOrder() {
        orderReceiverBean.receiveOrder(cookProperties.getOrder());
        paymentProcessorBean.processPayment(cookProperties.getPayment());
    }
}
