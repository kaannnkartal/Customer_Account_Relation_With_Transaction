package com.folksdev.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
    private String id;
    private String name;
    private String surname;
    private List<CustomerAccountDto> accounts;

    //Account Listesi vardı. Koymadık?

}
