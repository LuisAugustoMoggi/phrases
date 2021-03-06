package br.com.pipelivre.phrases.controllers;

import br.com.pipelivre.phrases.domains.Image;
import br.com.pipelivre.phrases.repositories.ImageRepository;
import br.com.pipelivre.phrases.usecases.AllowInsert;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final AllowInsert allowInsert;

    @GetMapping
    public List<Image> getImages(@RequestParam(value = "page", defaultValue = "-1") Integer page) {
        if (page == -1)
            return imageRepository.findAll();
        else
            return imageRepository.findPaginate(page);
    }

    @PostMapping
    public void createImage(@RequestBody Image image) {
        allowInsert.execute();
        imageRepository.insert(image);
    }


}
