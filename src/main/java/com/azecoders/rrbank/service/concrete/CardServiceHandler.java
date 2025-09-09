package com.azecoders.rrbank.service.concrete;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.dao.entity.CardEntity;
import com.azecoders.rrbank.dao.entity.UserEntity;
import com.azecoders.rrbank.dao.repository.BankAccountRepository;
import com.azecoders.rrbank.dao.repository.CardRepository;
import com.azecoders.rrbank.dao.repository.UserRepository;
import com.azecoders.rrbank.exception.AccountFoundException;
import com.azecoders.rrbank.exception.UserFoundException;
import com.azecoders.rrbank.model.enums.CardStatus;
import com.azecoders.rrbank.model.enums.CardType;
import com.azecoders.rrbank.model.enums.OrderStatus;
import com.azecoders.rrbank.model.requests.CreateCardRequest;
import com.azecoders.rrbank.model.response.CardResponse;
import com.azecoders.rrbank.service.abstraction.CardService;
import com.azecoders.rrbank.util.CardNumberGenerator;
import com.azecoders.rrbank.util.VerificationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CardServiceHandler implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final BankAccountRepository accountRepository;
    private final MailServiceHandler mailServiceHandler;

    @Override
    public CardResponse orderCard(CreateCardRequest cardRequest, String email, CardType cardType) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserFoundException("User not found", HttpStatus.NOT_FOUND));


        BankAccountEntity account = accountRepository.findById(cardRequest.getAcoountId())
                .orElseThrow(() -> new AccountFoundException("Account not found", HttpStatus.NOT_FOUND));


        if (!account.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("This account does not belong to the user");
        }

        if (account.getAccountStatus() != OrderStatus.APPROVED) {
            throw new AccountFoundException("Account not found!", HttpStatus.NOT_FOUND);
        }
        boolean cardExists = cardRepository.existsByAccount_User_IdAndCardType(user.getId(), cardType);
        if (cardExists) {
            throw new RuntimeException("User already has a card of this type");
        }


        CardEntity card = CardEntity.builder()
                .cardNumber(CardNumberGenerator.generateMasterCard())
                .cvv(CardNumberGenerator.generateCVV())
                .cardType(cardType)
                .expiryDate(LocalDate.now().plusYears(3))
                .cardStatus(CardStatus.PENDING)
                .account(account)
                .build();

        cardRepository.save(card);

        return CardResponse.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .expiryDate(card.getExpiryDate()
                        .format(DateTimeFormatter.ofPattern("MM/yy")))
                .cardStatus(CardStatus.valueOf(card.getCardStatus().name()))
                .cardType(CardType.valueOf(card.getCardType().name()))
                .accountNumber(account.getAccountNumber())
                .build();
    }


}
