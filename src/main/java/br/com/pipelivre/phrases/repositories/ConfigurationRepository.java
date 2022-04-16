package br.com.pipelivre.phrases.repositories;

import br.com.pipelivre.phrases.domains.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class ConfigurationRepository {
    private final JdbcTemplate jdbcTemplate;

    private Configuration buildEntity(ResultSet rs, int rowNum) throws SQLException {
        return new Configuration(
                rs.getLong("CONFIGURATION_ID"),
                rs.getBoolean("ALLOW_INSERTS")
        );
    }

    public Configuration findConfiguration() {
        return jdbcTemplate.query(
                "SELECT" +
                        " CONFIGURATION_ID," +
                        " ALLOW_INSERTS " +
                        " FROM T_CONFIGURATION " +
                        " WHERE CONFIGURATION_ID = ?",
                this::buildEntity,
                1
        ).get(0);
    }
}
