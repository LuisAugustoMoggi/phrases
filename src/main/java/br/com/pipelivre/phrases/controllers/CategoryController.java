package br.com.pipelivre.phrases.controllers;

import br.com.pipelivre.phrases.domains.Category;
import br.com.pipelivre.phrases.repositories.CategoryRepository;
import br.com.pipelivre.phrases.usecases.AllowInsert;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final AllowInsert allowInsert;

    @GetMapping
    public List<Category> getCategories(@RequestParam(value = "page", defaultValue = "-1") Integer page) {
        if (page == -1)
            return categoryRepository.findAll();
        else
            return categoryRepository.findPaginate(page);
    }

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        allowInsert.execute();
        categoryRepository.insert(category);
    }


}
