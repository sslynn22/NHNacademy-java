package spring.boot.core.day1.demo.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import spring.boot.core.day1.demo.bean.ChefBean;
import spring.boot.core.day1.demo.bean.DeliveryServiceBean;
import spring.boot.core.day1.demo.bean.OrderProcessorBean;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {
    private final OrderProcessorBean orderProcessorBean;
    private final ChefBean chefBean;
    private final DeliveryServiceBean deliveryServiceBean;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderProcessorBean.processOrder();
        chefBean.cook();
        deliveryServiceBean.deliver();
    }
}
