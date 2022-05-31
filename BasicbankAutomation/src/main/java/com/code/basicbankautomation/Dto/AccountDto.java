package com.code.basicbankautomation.Dto;


import com.code.basicbankautomation.model.Currency;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {
    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;
}