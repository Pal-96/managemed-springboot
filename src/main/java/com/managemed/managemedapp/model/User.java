package com.managemed.managemedapp.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component

@NamedStoredProcedureQuery(
    name = "User.addUser",
    procedureName = "add_user",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_username", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_frstname", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_lastname", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_hashpwd", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_role_id", type = Integer.class)
    }
)
@Entity
@Table(name = "USERTB")
public class User{
    @Id
    @Column(name = "USERNAME", length = 20)
    private String username;

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String frstname;

    @Column(name = "LASTNAME", length = 50)
    private String lastname; 

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return frstname;
    }

    public void setFirstname(String frstname) {
        this.frstname = frstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public String getRoleName() {
        return role.getRoleName();
    }



    
}
