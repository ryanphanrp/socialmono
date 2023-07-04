package org.ryan.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ryan.domain.constant.UserStatus;

import java.io.Serializable;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ofDefault();

    @Column(unique = true)
    private String email;
    private String password;
}
