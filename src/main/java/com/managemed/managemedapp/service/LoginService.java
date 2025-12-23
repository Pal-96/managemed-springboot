package com.managemed.managemedapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.UserRepository;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.security.PasswordUtil;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public LoginResult authenticate(String username, String password) {
        boolean result = userRepository.findByUsername(username)
                .map(user -> PasswordUtil.verifyPassword(password, user.getPassword()))
                .orElse(false);
        if (!result) {
            return LoginResult.failure();
        }
        String role = getRole(username);
        String token = JWTUtil.generateToken(username, role);
        return LoginResult.success(token);
    }

    public String getRole(String username) {
        return userRepository.findByUsername(username)
            .map(User::getRoleName)
            .orElseThrow(() ->
                new IllegalStateException("Role not found for user: " + username)
            );
    }

    public static class LoginResult {
        private final boolean success;
        private final String token;

        private LoginResult(boolean success, String token) {
            this.success = success;
            this.token = token;
        }

        public static LoginResult success(String token) {
            return new LoginResult(true, token);
        }

        public static LoginResult failure() {
            return new LoginResult(false, null);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getToken() {
            return token;
        }
    }
}
