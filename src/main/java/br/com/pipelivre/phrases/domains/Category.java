package br.com.pipelivre.phrases.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private Long categoryId;
    private String name;
    private String imageUrl;
    private Long numberOfPhrases;
}
