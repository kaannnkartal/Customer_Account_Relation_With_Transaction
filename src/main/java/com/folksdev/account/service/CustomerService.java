package com.folksdev.account.service;

import com.folksdev.account.dto.CustomerDto;
import com.folksdev.account.dto.CustomerDtoConverter;
import com.folksdev.account.exception.CustomerNotFoundException;
import com.folksdev.account.model.Customer;
import com.folksdev.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;


    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter){
        this.customerRepository = customerRepository;
        this.converter = converter;
    }


    // Account Servisinde accountu ilgilendiren customer bilgisi var. Bunun iliskisi oneToMany olarak kurulmustu.
    // O yüzden Account servisinde kullanılacak customer bilgisini bu serviste cekip oraya paslamam gerekiyor.
    // bunu yqparken entity paslayabiliriz.

    protected Customer findCustomerById(String id){
        // finfById optional bir değer donuyor. Null da donebilir dolu da donebilir.
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer could not by id"+ id));
    }

    public CustomerDto getCustomerById(String customerId){
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
