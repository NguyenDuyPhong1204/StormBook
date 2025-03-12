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

    public void sendMail(Long userId, String toEmail, String fullName){
        String titleEmail = "Cảm ơn bạn đã đăng ký tài khoản";
        String confirmUrl = serverHost + "api/auth/verifyAccount/" + userId;
        String contentEmail = String.format(
                "<body style=\"font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;\">\n" +
                        "    <div style=\"max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\">\n" +
                        "        <div style=\"text-align: center;\">\n" +
                        "            <img src=\"https://res.cloudinary.com/dgo9xrxhu/image/upload/v1741807353/cubdpdh2pvcjlicplxbp.png\" alt=\"Logo\" width=\"80\">\n" +
                        "            <h2 style=\"color: #5865F2;\">StormBook</h2>\n" +
                        "        </div>\n" +
                        "        <p>Xin chào <b>%s</b>,</p>\n" +
                        "        <p>Cảm ơn bạn đã đăng ký tài khoản. Vui lòng ấn nút \"Xác nhận\" bên dưới để kích hoạt tài khoản.</p>\n" +
                        "        <p>Nếu bạn không đăng ký, vui lòng bỏ qua email này.</p>\n" +
                        "        <div style=\"text-align: center; margin: 20px 0;\">\n" +
                        "            <a href=\"%s\" style=\"background-color: #5865F2; color: #fff; padding: 12px 24px; text-decoration: none; border-radius: 5px; font-size: 16px; display: inline-block;\">\n" +
                        "                Xác nhận\n" +
                        "            </a>\n" +
                        "        </div>\n" +
                        "        <hr style=\"border: none; border-top: 1px solid #ddd; margin: 20px 0;\">\n" +
                        "        <p style=\"text-align: center; color: grey; font-size: 14px;\">Được gửi bởi StormBook</p>\n" +
                        "    </div>\n" +
                        "</body>", fullName, confirmUrl // Truyền đúng username và URL xác nhận
        );

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
