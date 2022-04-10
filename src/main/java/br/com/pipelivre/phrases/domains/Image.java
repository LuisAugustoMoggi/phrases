package br.com.pipelivre.phrases.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    private Long imageId;
    private String imageUrl;
}
