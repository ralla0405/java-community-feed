package store.kirinit.communityfeed.auth.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.kirinit.communityfeed.auth.application.EmailService;
import store.kirinit.communityfeed.auth.application.dto.SendEmailRequestDto;
import store.kirinit.communityfeed.common.ui.Response;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendVerificationEmail(@RequestBody SendEmailRequestDto dto) {
      emailService.sendEmail(dto);

      return Response.ok(null);
    }

    @GetMapping("/verify-token")
    public Response<Void> verifyEmail(String email, String token) {
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }
}
