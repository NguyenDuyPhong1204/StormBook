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
    private Role role;
    private String avatar;
    private Boolean verified;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
}

