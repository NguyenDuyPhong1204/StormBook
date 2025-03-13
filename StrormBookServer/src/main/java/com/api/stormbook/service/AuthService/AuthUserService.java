package com.api.stormbook.service.AuthService;

import com.api.stormbook.dto.AuthDTO.AuthRequest;
import com.api.stormbook.dto.AuthDTO.UserDTO;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.ExistingException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.service.MailService.EmailService;
import com.api.stormbook.service.MailService.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OTPService otpService;

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
        User newUser = new User(authRequest.getEmail(), hashedPassword, authRequest.getFullName());
        authUserRepository.save(newUser);
        emailService.sendMail(newUser.getId(), newUser.getEmail(), newUser.getFullName());
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

}
