package com.company.webapp.repository;

import com.company.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where lower(u.username) like lower(:nameToFind)")
    User findByUsername(@Param("nameToFind") String username);
}
