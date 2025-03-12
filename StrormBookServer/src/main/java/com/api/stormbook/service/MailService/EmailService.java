package com.api.stormbook.service.MailService;

import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String formMail;

    @Value("${server.host}")
    private String serverHost;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(Long userId, String toEmail){
        String titleEmail = "Cảm ơn bạn đã đăng ký tài khoản";
        String confirmUrl = serverHost + "api/auth/verifyAccount/" + userId;
        String contentEmail = "<p>Nhấp vào liên kết dưới đây để xác nhận tài khoản của bạn: </p>"
                + "<a href=\""+confirmUrl + "\">Xác nhận tài khoản</a>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(formMail);
            helper.setTo(toEmail);
            helper.setSubject(titleEmail);
            helper.setText(contentEmail, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
