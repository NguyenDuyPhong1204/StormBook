package com.api.stormbook.service.AuthService;

import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    @Autowired
    private AuthUserRepository authUserRepository;



}
