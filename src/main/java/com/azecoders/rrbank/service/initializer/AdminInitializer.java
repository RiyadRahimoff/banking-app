package com.azecoders.rrbank.service.initializer;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.model.enums.UserRole;
import com.azecoders.rrbank.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
//public class AdminInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    @Override
//    public void run(String... args) throws Exception {
//
//        UserEntity admin = new UserEntity();
//        admin.setFullName("Riyad Rahimov");
//        admin.setUserRole(UserRole.ADMIN);
//        admin.setEmail("riyadrahimli777@gmail.com");
//        admin.setPassword(passwordEncoder.encode("riyad555"));
//        admin.setVerified(true);
//        admin.setUserStatus(UserStatus.ACTIVE);
//        userRepository.save(admin);
//
//    }
//}
