package org.ryan.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_infos")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userInfoId;

    @Column(unique = true)
    private Long userId;
    private String bio;
    private String name;
    private Integer followers;
    private Integer following;
    private Integer numPosts;
}
