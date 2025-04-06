package spring.boot.core.day1.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.core.day1.demo.bean.ChefBean;
import spring.boot.core.day1.demo.bean.DeliveryServiceBean;

@Configuration
public class ChefDeliveryConfig {
    @Bean
    public ChefBean chefBean() {
        return new ChefBean();
    }

    @Bean
    public DeliveryServiceBean deliveryServiceBean() {
        return new DeliveryServiceBean();
    }
}
