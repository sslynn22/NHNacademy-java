package spring.boot.core.day2.demo.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ChefBean {
    @Value("${cook.cooking}")
    private String foodCook;

    public void cook() {
        System.out.println(foodCook);
    }
}