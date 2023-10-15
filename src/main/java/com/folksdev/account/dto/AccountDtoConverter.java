package com.folksdev.account.dto;

import com.folksdev.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Data
public class AccountDtoConverter {

    // map struct ile yapılabilir, fakat test edilebilirlik olarak kendimiz converter yazmak daha iyi olur.
    // map struct annotation processing islemi yapıyor lombok gibi. Generate etmesin.

    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;



    public AccountDto convert(Account from){
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                from.getTransaction()
                        .stream()
                        .map(transactionDtoConverter::convert).collect(Collectors.toList()));
    }
}
