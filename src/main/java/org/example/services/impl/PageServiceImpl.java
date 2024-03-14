package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.PageRequest;
import org.example.entities.Page;
import org.example.repository.PageRepository;
import org.example.services.PageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;


    public Page save(PageRequest pageRequest) {
        Page page = new Page();
        page.setTitle(pageRequest.getTitle());
        page.setContent(pageRequest.getContent());
        page.setLayoutId(pageRequest.getLayoutId());
        return pageRepository.save(page);
    }

    public Optional<Page> findById(Integer id) {
        return pageRepository.findById(id);
    }
}
