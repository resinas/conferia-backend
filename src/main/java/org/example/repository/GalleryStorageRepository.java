package org.example.repository;

import org.example.entities.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryStorageRepository extends JpaRepository<GalleryImage, Integer> {
}
