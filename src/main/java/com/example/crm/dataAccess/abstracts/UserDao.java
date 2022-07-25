package com.example.crm.dataAccess.abstracts;

import com.example.crm.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.entityStatus=1 where u.id =:id")
    void delete(@Param("id") Integer id);

    @Modifying
    @Query("update User u set u.entityStatus=0 where u.id =:id")
    void activate(@Param("id") Integer id);

    @Modifying
    @Query("update User u set u.entityStatus=2 where u.id =:id")
    void disable(@Param("id") Integer id);

}
