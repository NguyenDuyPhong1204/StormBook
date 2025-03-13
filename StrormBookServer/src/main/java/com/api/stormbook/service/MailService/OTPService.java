package com.api.stormbook.service.MailService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
@Getter
@Setter
public class OTPService {
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    //tao otp ngau nhien 6 so
    private String generateOTP() {
        return String.valueOf(100000+random.nextInt(999999));
    }

    //luu otp vao cache voi thoi gian 5 phut
    public String createOTP(String email) {
        String otp = generateOTP();
        otpCache.put(email, otp);
        //xoa sau 5 phut
        new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(5);
                otpCache.remove(email);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return otp;
    }

    //kiem tra otp hop le
    public Boolean validateOTP(String email, String otp) {
        return otpCache.containsKey(email) && otpCache.get(email).equals(otp);
    }

    //xoa sau khi su dung
    public void removeOTP(String email) {
        otpCache.remove(email);
    }

}
