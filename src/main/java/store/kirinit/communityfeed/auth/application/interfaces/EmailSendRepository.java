package store.kirinit.communityfeed.auth.application.interfaces;

import store.kirinit.communityfeed.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String randomToken);
}
