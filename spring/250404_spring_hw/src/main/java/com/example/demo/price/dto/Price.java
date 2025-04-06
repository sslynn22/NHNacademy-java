package com.example.demo.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    @JsonProperty("순번")
    long id;
    @JsonProperty("지자체명")
    String city;
    @JsonProperty("업종")
    String sector;
    @JsonProperty("구간금액(원)")
    int unitPrice;

    @Override
    public String toString() {
        return "Price(" +
                "id=" + id +
                ", city=" + city +
                ", sector=" + sector +
                ", unitPrice=" + unitPrice +
                ")";
    }
}
