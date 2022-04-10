package br.com.pipelivre.phrases.controllers;

import br.com.pipelivre.phrases.domains.Info;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/info", "/"})
public class InfoController {

    @GetMapping
    public Info info() {
        return new Info("OK");
    }
}
