package com.azecoders.rrbank.dao.entity;

import com.azecoders.rrbank.model.enums.PhonePrefixs;
import com.azecoders.rrbank.model.enums.UserRole;
import com.azecoders.rrbank.model.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    String phoneNumber;

    @Enumerated(STRING)
    PhonePrefixs phonePrefix;

    LocalDate birthDate;

    boolean isVerified;

    @Enumerated(STRING)
    UserStatus userStatus;

    @Enumerated(STRING)
    UserRole userRole;

    String otpCode;

    LocalDateTime otpExpiry;

    String address;

    String nationalId;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


}
