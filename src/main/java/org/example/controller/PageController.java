package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.entities.Page;
import org.example.services.PageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.dto.PageRequest;

@RestController
@RequestMapping("/api/v1/pages")
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;

    @PostMapping
    public ResponseEntity<Page> createOrUpdatePage (@RequestBody PageRequest pageRequest) {
        return ResponseEntity.ok(pageService.save(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Integer id) {
        return ResponseEntity.ok(pageService.findById(id).orElseThrow(() -> new RuntimeException("Page not found")));
    }

}
