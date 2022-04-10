package br.com.pipelivre.phrases.repositories;

import br.com.pipelivre.phrases.domains.Category;
import br.com.pipelivre.phrases.domains.Phrase;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static br.com.pipelivre.phrases.PhrasesApplication.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class PhraseRepository {
    private final JdbcTemplate jdbcTemplate;

    private Phrase buildPhrase(ResultSet rs, int rowNum) throws SQLException {
        return new Phrase(
                rs.getLong("PHRASE_ID"),
                rs.getString("PHRASE"),
                new Date(rs.getLong("CREATED_ON")),
                new Category(
                        rs.getLong("CATEGORY_ID"),
                        rs.getString("NAME"),
                        null,
                        null
                )
        );
    }

    public void insert(Phrase phrase) {
        jdbcTemplate.update(
                "INSERT INTO T_PHRASES(PHRASE, CREATED_ON, CATEGORY_ID) VALUES (?, ?, ?)",
                phrase.getPhrase(),
                new Date().getTime(),
                phrase.getCategory().getCategoryId()
        );
    }

    public List<Phrase> findAll() {
        return jdbcTemplate.query(
                "SELECT " +
                        " PHRASE_ID, " +
                        " PHRASE, " +
                        " CREATED_ON, " +
                        " T_CATEGORIES.CATEGORY_ID, " +
                        " NAME " +
                        " FROM T_PHRASES " +
                        " INNER JOIN T_CATEGORIES ON T_CATEGORIES.CATEGORY_ID = T_PHRASES.CATEGORY_ID ",
                this::buildPhrase
        );
    }

    public List<Phrase> findPaginate(Integer page) {
        return jdbcTemplate.query(
                "SELECT " +
                        " PHRASE_ID, " +
                        " PHRASE, " +
                        " CREATED_ON, " +
                        " T_CATEGORIES.CATEGORY_ID, " +
                        " NAME " +
                        " FROM T_PHRASES " +
                        " INNER JOIN T_CATEGORIES ON T_CATEGORIES.CATEGORY_ID = T_PHRASES.CATEGORY_ID " +
                        " ORDER BY PHRASE_ID OFFSET ? LIMIT ?",
                this::buildPhrase,
                (page - 1) * DEFAULT_PAGE_SIZE,
                DEFAULT_PAGE_SIZE
        );
    }

    public List<Phrase> findPaginateByCategory(Integer page, Long categoryId) {
        return jdbcTemplate.query(
                "SELECT " +
                        " PHRASE_ID, " +
                        " PHRASE, " +
                        " CREATED_ON, " +
                        " T_CATEGORIES.CATEGORY_ID, " +
                        " NAME " +
                        " FROM T_PHRASES " +
                        " INNER JOIN T_CATEGORIES ON T_CATEGORIES.CATEGORY_ID = T_PHRASES.CATEGORY_ID " +
                        " WHERE T_CATEGORIES.CATEGORY_ID = ? " +
                        " ORDER BY PHRASE_ID OFFSET ? LIMIT ?",
                this::buildPhrase,
                categoryId,
                (page - 1) * DEFAULT_PAGE_SIZE,
                DEFAULT_PAGE_SIZE
        );
    }
}
