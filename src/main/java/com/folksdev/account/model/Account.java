package com.folksdev.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(generator = "UUID") // hashcode ile olusur. 32 digit. harf ve sayı.
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private BigDecimal balance;
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    private List<Transaction> transaction;

    public Account(Customer customer,BigDecimal balance, LocalDateTime creationDate) {
        this.id = "";
        this.customer = customer;
        this.balance = balance;
        this.creationDate = creationDate;
        this.transaction = new ArrayList<Transaction>();
    }

    // OneToMany , ManyToOne directional iliski kurduğumuz icin hashcodu'umuzu kendimiz üretmemiz gerekiyor.
    // Obur türlü loopa girmis oluyor nesnemiz, hashcodelar üzerinden karsılasma yaptığı icin.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(balance, account.balance) && Objects.equals(creationDate, account.creationDate) && Objects.equals(customer, account.customer) && Objects.equals(transaction, account.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, creationDate,customer);
    }
}

