package com.folksdev.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID") // hashcode ile olusur. 32 digit. harf ve sayı.
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "account_id" , nullable = false)
    private Account account;



    public Transaction( Account account, BigDecimal amount){
        this.id = "";
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.type = "Initial";
        this.account = account;
    }

    public Transaction(BigDecimal amount,Account account){
        this.id = "";
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.type = "Initial";
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount, transactionDate);
    }
}


/*enum class TransactionType{
    INITAL, TRANSFER
} */
