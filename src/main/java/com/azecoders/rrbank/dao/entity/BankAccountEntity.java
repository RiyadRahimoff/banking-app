package com.azecoders.rrbank.dao.entity;

import com.azecoders.rrbank.model.enums.AccountCurrency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = PRIVATE)
@Table(name = "accounts")
@Entity
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String accountNumber;

     BigDecimal balance;

     @Enumerated(EnumType.STRING)
     AccountCurrency accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
     UserEntity user;

    @CreationTimestamp
     LocalDateTime createdAt;

    @UpdateTimestamp
     LocalDateTime updatedAt;
}
