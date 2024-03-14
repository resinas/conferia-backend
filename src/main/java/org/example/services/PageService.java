package org.example.services;

import org.example.dto.PageRequest;
import org.example.entities.Page;

import java.util.Optional;

public interface PageService {
    Page save(PageRequest pageRequest);

    Optional<Page> findById(Integer id);
}

