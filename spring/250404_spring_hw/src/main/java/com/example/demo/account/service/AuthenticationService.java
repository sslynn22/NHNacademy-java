package com.example.demo.account.service;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Getter
    private Account currentAccount;
    private DataParser dataParser;

    @Autowired
    public AuthenticationService(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    public Account login(Long id, String password) {
        this.currentAccount = dataParser.accounts().stream()
                .filter(account -> account.getId() == id
                        && account.getPassword().equals(password))
                .findFirst().orElse(null);
        return this.currentAccount;
    }

    public void logout() {
        this.currentAccount = null;
    }
}
