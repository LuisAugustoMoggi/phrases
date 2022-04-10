package br.com.pipelivre.phrases.controllers;

import br.com.pipelivre.phrases.domains.Phrase;
import br.com.pipelivre.phrases.repositories.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/phrases")
@RequiredArgsConstructor
public class PhraseController {
    private final PhraseRepository phraseRepository;

    @GetMapping
    public List<Phrase> getCategories(@RequestParam(value = "page", defaultValue = "-1") Integer page) {
        if (page == -1)
            return phraseRepository.findAll();
        else
            return phraseRepository.findPaginate(page);
    }

    @PostMapping
    public void createPhrase(@RequestBody Phrase phrase) {
        phraseRepository.insert(phrase);
    }


}
