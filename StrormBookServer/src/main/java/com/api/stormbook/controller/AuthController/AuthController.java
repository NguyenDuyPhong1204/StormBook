package com.api.stormbook.controller.AuthController;

import com.api.stormbook.dto.AuthDTO.AuthGoogleRequest;
import com.api.stormbook.dto.AuthDTO.AuthRequest;
import com.api.stormbook.dto.AuthDTO.OTPRequest;
import com.api.stormbook.dto.AuthDTO.UserDTO;
import com.api.stormbook.entity.User;
import com.api.stormbook.entity.response.ApiNoDataResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.service.AuthService.AuthUserService;
import com.api.stormbook.service.MailService.EmailService;
import com.api.stormbook.service.MailService.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private OTPService otpService;

    @Autowired
    private AuthUserService authUserService;

    public AuthController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @GetMapping("/verifyAccount/{id}")
    public ResponseEntity<?> verifyAccount(@PathVariable Long id) {
//        Optional<User> optionalUser = authUserRepository.findById(id);
        User user = authUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getVerified()) {
            String htmlContent = "<html><head><meta charset='UTF-8'></head><body style='text-align: center; font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: orange;'>Tài khoản đã được xác nhận trước đó!</h2>" +
                    "<p>Bạn có thể đăng nhập ngay.</p>" +
                    "</body></html>";
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
        }

        user.setVerified(true);
        authUserRepository.save(user);
        String htmlContent = "<html><head><meta charset='UTF-8'></head><body style='text-align: center; font-family: Arial, sans-serif;'>" +
                "<h2 style='color: green;'>Xác nhận tài khoản thành công!</h2>" +
                "<p>Cảm ơn bạn đã đăng ký. Bạn có thể đăng nhập ngay bây giờ.</p>" +
                "</body></html>";

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        UserDTO result = authUserService.register(request);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đăng ký thành công. Vui lòng kiểm tra email của bạn để xác nhận tài khoản!", result);
        return ResponseEntity.ok(response);
    }

    //login google
    @PostMapping("/login-google")
    public ResponseEntity<?> loginGoogle(@RequestBody AuthGoogleRequest request){
        UserDTO userDTO = authUserService.loginWithGoogle(request);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công", userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        UserDTO result = authUserService.login(request);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công!", result);
        return ResponseEntity.ok(response);
    }

    //gui otp
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@RequestBody AuthRequest request){
        //gui email
        emailService.sendMailPassword(request.getEmail(), request.getFullName());
        ApiNoDataResponse apiNoDataResponse = new ApiNoDataResponse(HttpStatus.OK.value(), "Vui lòng kiểm tra email để nhận mã xác nhận");
        return ResponseEntity.ok(apiNoDataResponse);
    }

    //kiem tra otp
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestBody OTPRequest request) {
        if (otpService.validateOTP(request.getEmail(), request.getOtp())) {
            otpService.removeOTP(request.getEmail());
            return ResponseEntity.ok("OTP hợp lệ!");
        }
        ApiNoDataResponse apiNoDataResponse = new ApiNoDataResponse(HttpStatus.OK.value(), "Xác nhận OTP thành công");
        return ResponseEntity.ok(apiNoDataResponse);
    }

    //thay doi mat khau
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody AuthRequest request){
        UserDTO result = authUserService.updatePassword(request.getEmail(), request.getPassword());
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Thay đổi mật khẩu thành công", result);
        return ResponseEntity.ok(response);
    }










}
