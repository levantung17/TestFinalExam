package entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String proSkill;
    private int expInYear;
    private Role role;

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public enum Role {
        EMPLOYEE, ADMIN
    }
}