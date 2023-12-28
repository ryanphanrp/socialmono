package org.ryan.domain.dao;

import org.ryan.domain.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
  Page<UserInfo> findAllByUserIdIn(Iterable<Long> userIds, org.springframework.data.domain.Pageable pageable);
}
