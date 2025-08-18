package com.azecoders.rrbank.dao.entity;

import com.azecoders.rrbank.model.enums.PhonePrefixs;
import com.azecoders.rrbank.model.enums.UserRole;
import com.azecoders.rrbank.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = PRIVATE)
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String fullName;

    String email;

    String password;

    int loginAttempts;

    String phoneNumber;

    @Enumerated(STRING)
    PhonePrefixs phonePrefix;

    LocalDate birthDate;

    BigDecimal pendingWithdrawAmount;

    boolean isVerified;

    @Enumerated(STRING)
    UserStatus userStatus;

    @Enumerated(STRING)
    UserRole userRole;

    String otpCode;

    String address;

    String nationalId;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


}
