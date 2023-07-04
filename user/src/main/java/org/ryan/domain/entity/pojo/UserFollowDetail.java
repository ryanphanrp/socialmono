package org.ryan.domain.entity.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFollowDetail {
    Long followers;
    Long following;
}
