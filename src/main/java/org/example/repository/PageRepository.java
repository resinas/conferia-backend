package org.example.repository;

import org.example.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Integer> {
}
