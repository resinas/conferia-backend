package org.example.repository;

import org.example.entities.SessionHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface SessionHeaderRepository extends JpaRepository<SessionHeader, Long> {
    // In your SessionHeaderRepository
    List<SessionHeader> findByLikes_Id(Integer userId);

    @Query("SELECT sh.id FROM SessionHeader sh JOIN sh.likes u WHERE u.id = :userId")
    List<Long> findSessionIdsLikedByUser(@Param("userId") Integer userId);

}

