package com.managemed.managemedapp.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.model.Person;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.security.PasswordUtil;

@Service
public class RegisterService {

    private final DAOImpl dao;

    public RegisterService() {
        // Keeping your singleton DAO intact for now
        this.dao = DAOImpl.getInstance();
    }

    public boolean registerCustomer(String firstname, String lastname,
                                    String username, String password) {

        Person person = buildPerson(firstname, lastname, username, password);

        try {
            String result = dao.Connection();
            if ("Connection Established".equals(result)) {
                result = dao.Register(person, "Customer");
                return "User Registered".equals(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUser(String firstname, String lastname,
                           String username, String password, String role) {

        Person person = buildPerson(firstname, lastname, username, password);

        try {
            String result = dao.Register(person, role);
            return "User Registered".equals(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void editUser(String firstname, String lastname,
                         String username, String roleName) {

        try {
            dao.editUser(firstname, lastname, username, roleName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            dao.deleteUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person buildPerson(String firstname, String lastname,
                               String username, String password) {

        Person person = new Person();
        User user = new User();

        person.setFrstname(firstname);
        person.setLastname(lastname);

        user.setUsername(username);
        user.setPassword(PasswordUtil.hashPwd(password));

        person.setUser(user);
        return person;
    }
}