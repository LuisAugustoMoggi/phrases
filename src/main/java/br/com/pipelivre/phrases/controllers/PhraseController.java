package br.com.pipelivre.phrases.controllers;

import br.com.pipelivre.phrases.domains.Phrase;
import br.com.pipelivre.phrases.repositories.PhraseRepository;
import br.com.pipelivre.phrases.usecases.AllowInsert;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/phrases")
@RequiredArgsConstructor
public class PhraseController {
    private final AllowInsert allowInsert;
    private final PhraseRepository phraseRepository;

    @GetMapping
    public List<Phrase> getPhrases(@RequestParam(value = "page", defaultValue = "-1") Integer page) {
        if (page == -1)
            return phraseRepository.findAll();
        else
            return phraseRepository.findPaginate(page);
    }

    @GetMapping("/category/{categoryId}")
    public List<Phrase> getPhrasesByCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(value = "page", defaultValue = "1") Integer page) {
            return phraseRepository.findPaginateByCategory(page, categoryId);
    }

    @PostMapping
    public void createPhrase(@RequestBody Phrase phrase) {
        allowInsert.execute();
        phraseRepository.insert(phrase);
    }


}
