package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class MyCommands {

    private final AuthenticationService authenticationService;
    private final PriceService priceService;

    @ShellMethod(key = "login", value = "INPUT: login id password.")
    public String login(long id, String password) {
        Account currentAccount = this.authenticationService.login(id, password);
        if (Objects.isNull(currentAccount)) {
            throw new RuntimeException("Invalid id or password.");
        }
        return currentAccount.toString();
    }

    @ShellMethod(key = "logout", value = "INPUT: logout of the current user.")
    public String logout() {
        this.authenticationService.logout();
        return "good bye";
    }

    @ShellMethod(key = "current-user", value = "Show current login user info.")
    public String currentUser() {
        Account currentAccount = this.authenticationService.getCurrentAccount();
        return Objects.isNull(currentAccount) ? "good bye" : currentAccount.toString();
    }

    @ShellMethod(key = "city", value = "Show a list of cities.")
    public String city() {
        return this.priceService.cities().toString();
    }

    @ShellMethod(key = "sector", value = "Show a list of cities.")
    public String sector(String city) {
        return this.priceService.sectors(city).toString();
    }

    @ShellMethod(key = "price", value = "Show the price")
    public String price(String city, String sector) {
        return this.priceService.price(city, sector).toString();
    }

    @ShellMethod(key = "bill-total", value = "Calculate the total bill")
    public String billTotal(String city, String sector, int usage) {
        return this.priceService.billTotal(city, sector, usage);
    }


}