package ru.netology.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.diplom.dto.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByLogin(String login);

    @Modifying
    @Query("update User user set user.token = :token where user.login = :login")
    public void addTokenToUser(String login, String token);

    @Modifying
    @Query("update User user set user.token = null where user.token = :auth-token")
    public void removeToken(@Param("auth-token") String token);

}