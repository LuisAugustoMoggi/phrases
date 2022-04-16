package br.com.pipelivre.phrases.usecases;

import br.com.pipelivre.phrases.exceptions.InsertNotAllowed;
import br.com.pipelivre.phrases.repositories.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllowInsert {
    private ConfigurationRepository configurationRepository;

    public void execute() {
        if (!configurationRepository.findConfiguration().getAllowInserts())
            throw new InsertNotAllowed();
    }
}

