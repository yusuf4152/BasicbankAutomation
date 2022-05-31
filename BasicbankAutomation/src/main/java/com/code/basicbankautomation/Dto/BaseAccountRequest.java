package com.code.basicbankautomation.Dto;

import com.code.basicbankautomation.model.City;
import com.code.basicbankautomation.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BaseAccountRequest {
    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
