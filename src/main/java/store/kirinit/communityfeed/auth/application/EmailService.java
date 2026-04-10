package store.kirinit.communityfeed.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.kirinit.communityfeed.auth.application.dto.SendEmailRequestDto;
import store.kirinit.communityfeed.auth.application.interfaces.EmailSendRepository;
import store.kirinit.communityfeed.auth.application.interfaces.EmailVerificationRepository;
import store.kirinit.communityfeed.auth.domain.Email;
import store.kirinit.communityfeed.auth.domain.RandomTokenGenerator;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}
