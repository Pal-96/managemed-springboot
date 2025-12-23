package com.managemed.managemedapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.managemed.managemedapp.model.Roles;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.AddUserProcedureRepository;
import com.managemed.managemedapp.repository.RoleRepository;
import com.managemed.managemedapp.repository.UserRepository;
import com.managemed.managemedapp.security.PasswordUtil;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AddUserProcedureRepository procedureRepository;
    
    public boolean registerCustomer(String firstname, String lastname,
                                    String username, String password) {
        if (userRepository.existsById(username)) {
            return false;
        }
        Roles role = roleRepository.findByRoleName("Customer")
                .orElseThrow(() -> new IllegalArgumentException("Invalid role")); 
        User user = buildUser(firstname, lastname, username, password, role);
        procedureRepository.addUser(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                role.getRoleId()
        );

        return true;
    }

    public boolean addUser(String firstname, String lastname,
                           String username, String password, String roleName) {
        if (userRepository.existsById(username)) {
            return false;
        }
        Roles role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));
        User user = buildUser(firstname, lastname, username, password, role);

        procedureRepository.addUser(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                role.getRoleId()
        );

        return true;
    }

    public void editUser(String firstname, String lastname,
                         String username, String roleName) {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Roles role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    private User buildUser(String firstname, String lastname,
                               String username, String password, Roles role) {

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);

        user.setUsername(username);
        user.setPassword(PasswordUtil.hashPwd(password));
        user.setRole(role);
        return user;
    }
}