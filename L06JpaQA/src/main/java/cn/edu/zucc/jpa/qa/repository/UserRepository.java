package cn.edu.zucc.jpa.qa.repository;

import cn.edu.zucc.jpa.qa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Transactional
    UserEntity findById(int id);
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.img = :pic WHERE u.id = :id")
    void updatePicById(@Param("id") int id, @Param("pic") byte[] pic);
}
