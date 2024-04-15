package org.example.services;

import org.example.dto.requests.PageRequest;
import org.example.entities.Page;

import java.util.Optional;

public interface PageService {
    Page create(PageRequest pageRequest);

    Optional<Page> findById(Integer id);

    Page update (PageRequest pageRequest, Integer id);

}

