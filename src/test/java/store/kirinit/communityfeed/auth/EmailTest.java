package store.kirinit.communityfeed.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import store.kirinit.communityfeed.auth.domain.Email;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#@naver", "안녕@하세요.com"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab", "a@naver.com", "natty@naver", "test@test.com"})
    void givenEmailValidWhenCreateThenReturnEmail(String email) {
        // given

        // when
        Email emailValue = Email.createEmail(email);

        // then
        assertEquals(email, emailValue.getEmailText());
    }
}
