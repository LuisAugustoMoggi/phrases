package br.com.pipelivre.phrases.repositories;

import br.com.pipelivre.phrases.PhrasesApplication;
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
public class ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    private Image buildImage(ResultSet rs, int rowNum) throws SQLException {
        return new Image(
                rs.getLong("IMAGE_ID"),
                rs.getString("IMAGE_URL")
        );
    }

    public void insert(Image image) {
        jdbcTemplate.update("INSERT INTO T_IMAGES(IMAGE_URL) VALUES (?)", image.getImageUrl());
    }

    public List<Image> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM T_IMAGES",
                this::buildImage
        );
    }

    public List<Image> findPaginate(Integer page) {
        return jdbcTemplate.query(
                "SELECT * FROM T_IMAGES ORDER BY IMAGE_ID OFFSET ? LIMIT ?",
                this::buildImage,
                (page - 1) * DEFAULT_PAGE_SIZE,
                DEFAULT_PAGE_SIZE
        );
    }
}
