package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.model.requests.CreateUserInformationRequest;
import com.azecoders.rrbank.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.azecoders.rrbank.model.enums.ExceptionEnums.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;
    @Override
    public void updateInfo(CreateUserInformationRequest createUserInformationRequest,String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserFoundException("User not found:"+email, HttpStatus.BAD_REQUEST));

        user.setAddress(createUserInformationRequest.getAdress());

        LocalDate birthDate = createUserInformationRequest.getBirthDate();

        if (birthDate.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
        else {
            user.setBirthDate(birthDate);
        }
        user.setNationalId(createUserInformationRequest.getNationalId());
        userRepository.save(user);
    }
}
