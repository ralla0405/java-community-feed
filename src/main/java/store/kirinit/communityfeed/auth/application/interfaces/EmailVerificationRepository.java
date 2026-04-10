package store.kirinit.communityfeed.auth.application.interfaces;

import store.kirinit.communityfeed.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);
}
