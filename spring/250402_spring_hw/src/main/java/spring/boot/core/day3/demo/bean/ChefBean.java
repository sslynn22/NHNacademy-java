package spring.boot.core.day3.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Slf4j
public class ChefBean {
    @Value("${cook.cooking}")
    private String foodCook;

    public void cook() {
        //System.out.println(foodCook);
        log.info(foodCook);
    }
}
