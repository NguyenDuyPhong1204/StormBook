package com.api.stormbook.dto.AuthDTO;

import com.api.stormbook.entity.User.Status;
import com.api.stormbook.entity.User.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String email;
    private String fullName;
    private Role role = Role.USER;
    private String avatar = "";
    private Boolean verified = false;
    private Status status = Status.ACTIVE;
    private Instant createdAt;
    private Instant updatedAt;

//    public UserDTO(long id, String email, String fullName, Role role, String avatar, Boolean verified, Status status, Instant createdAt, Instant updatedAt) {
//        this.id = id;
//        this.email = email;
//        this.fullName = fullName;
//        this.role = role;
//        this.avatar = avatar;
//        this.verified = verified;
//        this.status = status;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
}

