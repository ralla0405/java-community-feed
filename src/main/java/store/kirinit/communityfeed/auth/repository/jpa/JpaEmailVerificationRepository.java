package store.kirinit.communityfeed.auth.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import store.kirinit.communityfeed.auth.repository.entity.EmailVerificationEntity;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {

    Optional<EmailVerificationEntity> findByEmail(String email);
}
