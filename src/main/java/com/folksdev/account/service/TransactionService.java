package com.folksdev.account.service;

import com.folksdev.account.model.Account;
import com.folksdev.account.model.Transaction;
import com.folksdev.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

// Bu servise ihtiyac yok direkt, new atıp kullandık transactionı.
@Service
public class TransactionService {

    //Logger classın icindeki metot, satır sayısına kadar bilgisini veriyor.
    // Boylelikle ben bir hata mesajının yada bilginin nereden üretildiğini gorebiliyorum.
    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(Account account, BigDecimal amount){
        Transaction transaction = new Transaction(account, amount);
        return transactionRepository.save(transaction);
    }



}
