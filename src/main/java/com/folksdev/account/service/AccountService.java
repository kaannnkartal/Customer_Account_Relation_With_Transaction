package com.folksdev.account.service;

import com.folksdev.account.dto.AccountDto;
import com.folksdev.account.dto.AccountDtoConverter;
import com.folksdev.account.dto.CreateAccountRequest;
import com.folksdev.account.model.Account;
import com.folksdev.account.model.Customer;
import com.folksdev.account.model.Transaction;
import com.folksdev.account.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    // Autowired yaptığımız zaman bu accoutn service immutable olmuyor.
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;

    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService, AccountDtoConverter converter){
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.converter = converter;
    }


    public AccountDto createAccount2(CreateAccountRequest createAccountRequest){
        // Asagısı bulanamazsa customer Serivsten zaten bir exception donmecek.
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        // id yi hibernate üretecek
        Account account = new Account(customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());


        // Compareto büyükse 1 esitse 0 kücükse -1 donuyor.
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = new Transaction(createAccountRequest.getInitialCredit(), account);
            //transaction.setAccount(account);

            account.getTransaction().add(transaction);


            // bu asagidaki control bug bozdu.
            System.out.println("Control : " + account.getTransaction());




        }


        return converter.convert(accountRepository.save(account));
    }


    public AccountDto createAccount3(CreateAccountRequest createAccountRequest){
        // Asagısı bulanamazsa customer Serivsten zaten bir exception donmecek.
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());


        // id yi hibernate üretecek
        Account account = new Account(customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());


        // Compareto büyükse 1 esitse 0 kücükse -1 donuyor.
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            //Transaction transaction = new Transaction( account, createAccountRequest.getInitialCredit());

            Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            account.getTransaction().add(transaction);
        }

        return converter.convert(accountRepository.save(account));
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        // Asagısı bulanamazsa customer Serivsten zaten bir exception donmecek.
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());


        // id yi hibernate üretecek
        Account account = new Account(customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());


        // Compareto büyükse 1 esitse 0 kücükse -1 donuyor.
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = new Transaction( account, createAccountRequest.getInitialCredit());
            account.getTransaction().add(transaction);
        }

        return converter.convert(accountRepository.save(account));

    }




}
