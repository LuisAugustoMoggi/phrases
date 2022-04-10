package br.com.pipelivre.phrases.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Phrase {
    private Long phraseId;
    private String phrase;
    private Date createdOn;
    private Category category;
}
