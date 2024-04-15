package org.example.repository;

import org.example.entities.SessionHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionHeaderRepository extends JpaRepository<SessionHeader, Long> {
}

