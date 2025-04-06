package spring.boot.core.day2.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.core.day2.demo.bean.ChefBean;
import spring.boot.core.day2.demo.bean.DeliveryServiceBean;
import spring.boot.core.day2.demo.bean.OrderProcessorBean;
import spring.boot.core.day2.demo.bean.PaymentProcessorBean;

@Configuration
public class CookConfig {

    @Bean
    public PaymentProcessorBean paymentProcessor() {
        return new PaymentProcessorBean();
    }

    @Bean
    public OrderProcessorBean orderProcessor() {
        return new OrderProcessorBean();
    }

    @Bean
    public ChefBean chefBean() {
        return new ChefBean();
    }

    @Bean
    public DeliveryServiceBean deliveryServiceBean() {
        return new DeliveryServiceBean();
    }
}
