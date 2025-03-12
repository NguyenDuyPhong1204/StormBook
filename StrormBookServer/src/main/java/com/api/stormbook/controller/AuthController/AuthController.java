package com.api.stormbook.controller.AuthController;

import com.api.stormbook.dto.AuthDTO.AuthRequest;
import com.api.stormbook.dto.AuthDTO.UserDTO;
import com.api.stormbook.entity.User;
import com.api.stormbook.entity.response.ApiMailResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.service.AuthService.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;

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
//        if (optionalUser.isEmpty()) {  // Kiểm tra nếu không tìm thấy user
//            ApiMailResponse response = new ApiMailResponse(HttpStatus.NOT_FOUND.value(), "Người dùng không tồn tại");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Trả về 404
//        }

//        User user = optionalUser.get();
//        System.out.println(user.getEmail() + user.getId());

        if (user.getVerified()) {
            ApiMailResponse response = new ApiMailResponse(HttpStatus.OK.value(), "Tài khoản của bạn đã xác nhận trước đó!");
            return ResponseEntity.ok(response);
        }

        user.setVerified(true);
        authUserRepository.save(user);
        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Xác nhận tài khoản thành công", user);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        UserDTO result = authUserService.register(request);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đăng ký thành công. Vui lòng kiểm tra email của bạn để xác nhận tài khoản!", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        UserDTO result = authUserService.login(request);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công!", result);
        return ResponseEntity.ok(response);
    }


















}
