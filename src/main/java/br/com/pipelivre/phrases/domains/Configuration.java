package br.com.pipelivre.phrases.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Configuration {
    private Long configurationId;
    private Boolean allowInserts;
}
