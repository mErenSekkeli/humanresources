package com.humanresources.webservice.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(nativeQuery = true,
        value = "SELECT users.* FROM users WHERE id = :userId")
    Users getUserById(@Param("userId") long id);

    @Query(nativeQuery = true,
        value = "SELECT users.* FROM users WHERE email = :email")
    Users getUserByEmail(@Param("email") String mail);

    @Query(nativeQuery = true,
        value = "SELECT users.* FROM users WHERE user_name = :userName")
    Users getUserByUserName(@Param("userName") String userName);


}
