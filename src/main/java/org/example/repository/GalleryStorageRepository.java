package org.example.repository;

import org.example.entities.GalleryImage;
import org.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryStorageRepository extends JpaRepository<GalleryImage, Integer> {
    Optional<GalleryImage> findByPath(String path);
}
