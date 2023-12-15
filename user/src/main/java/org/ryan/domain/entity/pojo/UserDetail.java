package org.ryan.domain.entity.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
