package spring.boot.core.day3.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("cook.delviery")
@Slf4j
public class DeliveryServiceBean {

    private String cookDelivery;
    public void deliver() {
        System.out.println(cookDelivery);
        log.info(cookDelivery);
    }
}