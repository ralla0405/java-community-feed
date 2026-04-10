package store.kirinit.communityfeed.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import store.kirinit.communityfeed.auth.application.interfaces.EmailSendRepository;
import store.kirinit.communityfeed.auth.domain.Email;
import store.kirinit.communityfeed.auth.repository.jpa.JpaEmailVerificationRepository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void sendEmail(Email email, String randomToken) {

    }
}
