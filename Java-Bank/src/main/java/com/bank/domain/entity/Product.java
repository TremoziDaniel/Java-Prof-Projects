package com.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @Size(message = "Product name length must be between 3 and 255 characters.",
            min = 3, max = 255)
    private String name;

    @Basic
    private boolean status;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private Double interestRate;

    @Column(name="product_limit")
    private Integer limit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", manager=" + manager.getId() +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currency=" + currency +
                ", interestRate=" + interestRate +
                ", limit=" + limit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
