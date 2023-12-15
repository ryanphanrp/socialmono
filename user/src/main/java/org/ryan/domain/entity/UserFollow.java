package org.ryan.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_follows")
public class UserFollow implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userFollowId;
  private Long fromUserId;
  private Long toUserId;
}
