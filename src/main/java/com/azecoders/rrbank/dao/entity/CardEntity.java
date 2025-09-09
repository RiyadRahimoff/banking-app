package com.azecoders.rrbank.dao.entity;

import com.azecoders.rrbank.model.enums.CardStatus;
<<<<<<< HEAD
import com.azecoders.rrbank.model.enums.CardType;
=======
>>>>>>> b8be9f1a352aeed605db1b55bd4251358224649d
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "cards")
@Entity
@Builder
@FieldDefaults(level = PRIVATE)
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cardNumber;

    String cvv;

    LocalDate expiryDate;

    @Enumerated(value = STRING)
    CardStatus cardStatus;

<<<<<<< HEAD
    @Enumerated(value = STRING)
    CardType cardType;

=======
>>>>>>> b8be9f1a352aeed605db1b55bd4251358224649d
    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    BankAccountEntity account;


}
