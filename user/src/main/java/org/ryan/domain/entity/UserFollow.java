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
@Table(name = "user_follows")
public class UserFollow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userFollowId;
    private Long fromUserId;
    private Long toUserId;
}
