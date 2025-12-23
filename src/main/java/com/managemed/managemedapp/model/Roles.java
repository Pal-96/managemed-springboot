package com.managemed.managemedapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Roles {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "roles_seq"
    )
    @SequenceGenerator(
        name = "roles_seq",
        sequenceName = "ROLES_SEQ",
        allocationSize = 1
    )
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    private String roleName;
    
    public int getRoleId() {
        return roleId;
    }

    public void setRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRole() {
        return roleName;
    }
}
