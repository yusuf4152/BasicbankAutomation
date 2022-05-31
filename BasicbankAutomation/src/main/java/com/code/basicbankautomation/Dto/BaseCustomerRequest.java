package com.code.basicbankautomation.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerRequest {
    private  String id;
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private String address;
}
