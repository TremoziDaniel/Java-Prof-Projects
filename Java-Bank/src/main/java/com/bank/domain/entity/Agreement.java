package com.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "agreements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Double interestRate;

    @Basic
    private boolean status;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private BigDecimal sum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", account=" + account.getId() +
                ", product=" + product.getId() +
                ", interestRate=" + interestRate +
                ", status=" + status +
                ", currency=" + currency +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
