package com.api.stormbook.service.AuthService;

import com.api.stormbook.dto.AuthDTO.AuthGoogleRequest;
import com.api.stormbook.dto.AuthDTO.AuthRequest;
import com.api.stormbook.dto.AuthDTO.UserDTO;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.ExistingException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.service.MailService.EmailService;
import com.api.stormbook.service.MailService.OTPService;
import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OTPService otpService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService, OTPService otpService) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.otpService = otpService;
    }

    //DANG KY
    public UserDTO register(AuthRequest authRequest){
        //kiem tra xem email da ton tai hay chua
        Optional<User> existingUser = authUserRepository.findByEmail(authRequest.getEmail());
        if(existingUser.isPresent()){
            throw new ExistingException("Email đã tồn tại");
        }
        //ma hoa mat khau
        String hashedPassword = passwordEncoder.encode(authRequest.getPassword());

        //luu user vao database
//        User newUser = new User.builder(authRequest.getEmail(), hashedPassword, authRequest.getFullName());
        User newUser = User.builder()
                .email(authRequest.getEmail())
                .password(hashedPassword)
                .fullName(authRequest.getFullName())
                .build();

        emailService.sendMail(newUser.getId(), newUser.getEmail(), newUser.getFullName());
        authUserRepository.save(newUser);
        return new UserDTO(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getFullName(),
                newUser.getRole(),
                newUser.getAvatar(),
                newUser.getVerified(),
                newUser.getStatus(),
                newUser.getCreatedAt(),
                newUser.getUpdatedAt()
        );
    }

    //dang nhap google
    public UserDTO loginWithGoogle(AuthGoogleRequest request){
        try {
            GoogleIdTokenVerifier verify = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();
            GoogleIdToken idToken = verify.verify(request.getIdToken());
            if(idToken == null){
                throw new ErrorException("Token Google không hợp lệ");
            }
            //lay thong tin tu payload
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String avatar = (String) payload.get("picture");
            //kiem tra xem nguoi dung da ton tai hay chua
            Optional<User> existingUser = authUserRepository.findByEmail(email);

            User user;
            if(existingUser.isPresent()){
                //cap nhat thong tin neu can
                user = existingUser.get();
                user.setFullName(name);
                user.setAvatar(avatar);
                authUserRepository.save(user);
            }else{
                //tao moi neu chua ton tai
                user = new User();
                user.setEmail(email);
                user.setFullName(name);
                user.setPassword(null);
                user.setAvatar(avatar);
                user.setVerified(true); // Đã xác thực qua Google
                user.setStatus(User.Status.ACTIVE);
                authUserRepository.save(user);
            }

            return new UserDTO(
                    user.getId(),
                    user.getEmail(),
                    user.getFullName(),
                    user.getRole(),
                    user.getAvatar(),
                    user.getVerified(),
                    user.getStatus(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
        }catch (Exception e){
            throw new ErrorException("Đăng nhập bằng Google thất bại: " + e.getMessage());
        }
    }

    //DANG NHAP
    public UserDTO login(AuthRequest request){
        //tim nguoi dung theo email
        User user = authUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("Email không tồn tại"));
        //kiem tra mat khau
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ErrorException("Mật khẩu không đúng!");
        }
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getAvatar(),
                user.getVerified(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    //Quen mat khau
    public UserDTO updatePassword(String email, String newPassword){
        //kiem tra ma otp hop le
//        if(!otpService.validateOTP(email, otp)){
//            throw new ErrorException("Mã OTP không hợp lệ hoặc đã hết hạn");
//        }

        //tim user theo email
        User user = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email không tồn tại"));

        //cap nhat mat khau moi
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);
        //xoa otp sau khi thanh cong
        otpService.removeOTP(email);

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getAvatar(),
                user.getVerified(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

//    private GoogleIdToken.Payload verifyGoogleToken(String idTokenString){
//        try{
//            GoogleIdTokenVerifier verify = new GoogleIdTokenVerifier.Builder(
//                    new NetHttpTransport(),
////                    JacksonFa
//            )
//        }catch (Exception e){
//            throw new ErrorException("Lỗi khi xác thực Google token");
//        }
//    }

}
