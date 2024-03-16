package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.entities.Page;
import org.example.repository.PageRepository;
import org.example.services.PageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.example.dto.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pages")
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;
    private final PageRepository pageRepository;

    //CRUD operations for managing page content.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Page> createPage (@RequestBody PageRequest pageRequest) {
        return ResponseEntity.ok(pageService.save(pageRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<Page> updatePage (@PathVariable Integer id, @RequestBody PageRequest pageRequest) {
        return ResponseEntity.ok(pageService.update(pageRequest, id));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Integer id) {
        return ResponseEntity.ok(pageService.findById(id).orElseThrow(() -> new RuntimeException("Page not found")));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Page>> getPages() {
        List<Page> pages = pageRepository.findAll();
        return ResponseEntity.ok(pages);
    }

}
