package com.folksdev.account.repository;

import com.folksdev.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {


    //@Query()
    //public Account updateAccountBy
}
