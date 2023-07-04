package org.ryan.domain.dao;

import org.ryan.domain.entity.pojo.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Long> {

    @Query(value = "select * from user_infos u join users usr using (user_id) where usr.username = :username", nativeQuery = true)
    Optional<UserDetail> findUserInfoByUsername(@Param("username") String username);
}
