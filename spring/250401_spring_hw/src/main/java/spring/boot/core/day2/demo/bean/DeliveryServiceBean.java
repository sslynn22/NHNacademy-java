package spring.boot.core.day2.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("cook.delviery")
public class DeliveryServiceBean {

    private String cookDelivery;
    public void deliver() {
        System.out.println(cookDelivery);
    }
}