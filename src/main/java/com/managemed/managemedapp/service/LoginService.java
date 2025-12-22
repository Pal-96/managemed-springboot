package com.managemed.managemedapp.service;

import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.security.JWTUtil;

@Service
public class LoginService {

    public LoginResult authenticate(String username, String password) {

        DAOImpl dao = DAOImpl.getInstance();

        try {
            String connectionResult = dao.Connection();
            if (!"Connection Established".equals(connectionResult)) {
                return LoginResult.failure();
            }

            String loginResult = dao.login(username, password);
            if (!"Login Succssful".equals(loginResult)) {
                return LoginResult.failure();
            }

            ResultSet rs = dao.getRole(username);
            String role = rs.next() ? rs.getString(1) : "USER";

            String token = JWTUtil.generateToken(username, role);
            return LoginResult.success(token);

        } catch (Exception e) {
            e.printStackTrace();
            return LoginResult.failure();
        }
    }

    // Simple result wrapper
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
