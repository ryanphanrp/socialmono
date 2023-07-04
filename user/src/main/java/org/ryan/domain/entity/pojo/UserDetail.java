package org.ryan.domain.entity.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.ryan.domain.constant.UserStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userInfoId;
    private Long userId;
    private String bio;
    private String name;
    private Integer followers;
    private Integer following;
    private Integer numPosts;
    private String username;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
    private String password;
}
