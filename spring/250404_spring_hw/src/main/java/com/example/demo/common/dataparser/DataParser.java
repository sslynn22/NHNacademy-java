package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;

import java.util.List;

public interface DataParser {

    List<String> cities();

    List<String> sectors(String city);

    Price price(String city, String sector);

    List<Account> accounts();
}
