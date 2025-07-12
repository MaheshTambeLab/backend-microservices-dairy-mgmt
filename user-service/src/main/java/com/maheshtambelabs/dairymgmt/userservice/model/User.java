package com.maheshtambelabs.dairymgmt.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public abstract UserType getUserType();

    public void update(User updatedUser) {
        if (updatedUser.getName() != null) this.name = updatedUser.getName();
        if (updatedUser.getEmail() != null) this.email = updatedUser.getEmail();
        if (updatedUser.getPhone() != null) this.phone = updatedUser.getPhone();
        if (updatedUser.getAddress() != null) this.address = updatedUser.getAddress();
        if (updatedUser.getPassword() != null) this.password = updatedUser.getPassword();
    }
}

