package br.com.pipelivre.phrases.repositories;

import br.com.pipelivre.phrases.domains.Category;
import br.com.pipelivre.phrases.domains.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static br.com.pipelivre.phrases.PhrasesApplication.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    private Category buildImage(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getLong("CATEGORY_ID"),
                rs.getString("NAME"),
                rs.getString("IMAGE_URL"),
                rs.getLong("PHRASES")
        );
    }

    public void insert(Category category) {
        jdbcTemplate.update(
                "INSERT INTO T_CATEGORIES(NAME, IMAGE_URL) VALUES (?, ?)",
                category.getName(),
                category.getImageUrl()
        );
    }

    public List<Category> findAll() {
        return jdbcTemplate.query(
                "SELECT" +
                        " T_CATEGORIES.CATEGORY_ID," +
                        " NAME," +
                        " IMAGE_URL," +
                        " COUNT(PHRASE_ID) AS PHRASES " +
                        " FROM T_CATEGORIES " +
                        " LEFT JOIN T_PHRASES ON T_CATEGORIES.CATEGORY_ID = T_PHRASES.CATEGORY_ID " +
                        " GROUP BY T_CATEGORIES.CATEGORY_ID " +
                        " ORDER BY CATEGORY_ID",
                this::buildImage
        );
    }

    public List<Category> findPaginate(Integer page) {
        return jdbcTemplate.query(
                "SELECT" +
                        " T_CATEGORIES.CATEGORY_ID," +
                        " NAME," +
                        " IMAGE_URL," +
                        " COUNT(PHRASE_ID) AS PHRASES " +
                        " FROM T_CATEGORIES " +
                        " LEFT JOIN T_PHRASES ON T_CATEGORIES.CATEGORY_ID = T_PHRASES.CATEGORY_ID " +
                        " GROUP BY T_CATEGORIES.CATEGORY_ID " +
                        " ORDER BY CATEGORY_ID OFFSET ? LIMIT ?",
                this::buildImage,
                (page - 1) * DEFAULT_PAGE_SIZE,
                DEFAULT_PAGE_SIZE
        );
    }
}
