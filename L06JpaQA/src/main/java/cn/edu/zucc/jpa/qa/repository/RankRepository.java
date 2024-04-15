package cn.edu.zucc.jpa.qa.repository;

import cn.edu.zucc.jpa.qa.entity.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Optional;

public interface RankRepository extends JpaRepository<RankEntity,Integer> {
    @Transactional
    RankEntity findById(int id);
    @Transactional
    @Modifying
    @Query("UPDATE RankEntity r SET r.score = :time WHERE r.id = :id")
    void updateScoreById(@Param("id") int id, @Param("time") double time);
}
