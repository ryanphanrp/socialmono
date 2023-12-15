package org.ryan.domain.dao;

import java.util.Optional;
import org.ryan.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

  Optional<User> findUserByUserId(@Param("userId") Long userId);

  Optional<User> findUserByUsername(@Param("username") String username);
}
