package store.kirinit.communityfeed.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.auth.application.interfaces.EmailVerificationRepository;
import store.kirinit.communityfeed.auth.domain.Email;
import store.kirinit.communityfeed.auth.repository.entity.EmailVerificationEntity;
import store.kirinit.communityfeed.auth.repository.jpa.JpaEmailVerificationRepository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if (emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("이미 인증된 이메일입니다");
            }

            emailVerificationEntity.updateToken(token);

            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailAddress = email.getEmailText();
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailAddress)
            .orElseThrow(() -> new IllegalArgumentException("인증요청하지 않은 이메일입니다."));

        if (entity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다.");
        }

        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰값이 유효하지 않습니다.");
        }

        entity.verify();
    }
}
