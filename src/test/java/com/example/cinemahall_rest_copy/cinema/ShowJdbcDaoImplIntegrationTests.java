package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ShowJdbcDaoImplIntegrationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ShowJdbcDaoImpl showJdbcDao;

    @BeforeEach
    public void setUp() {
        String sql = "DELETE FROM shows";
        jdbcTemplate.update(sql);
    }

    @Test
    public void testThatShowCanBeCreated() {
        // given
        Show show = TestDataUtil.createTestShowA();
        // when
        UUID uuid = showJdbcDao.create(show);
        // then
        assertThat(uuid).isNotNull();
    }
    @Test
    public void testThatMultipleShowsCanBeCreatedANdRecalled() {
        // given
        Show showA = TestDataUtil.createTestShowA();
        showJdbcDao.create(showA);
        Show showB = TestDataUtil.createTestShowB();
        showJdbcDao.create(showB);
        Show showC = TestDataUtil.createTestShowC();
        showJdbcDao.create(showC);
        // when
        List<Show> result = showJdbcDao.findMany();
        // then
        assertThat(result)
                .hasSize(3)
                .contains(showA, showB, showC);
    }

    @Test
    public void testThatShowCanBeUpdated() {
        // given
        Show show = TestDataUtil.createTestShowA();
        showJdbcDao.create(show);
        // when
        show.setMovie_title("UPDATED");
        showJdbcDao.update(show.getId(), show);
        // then
        Optional<Show> result = showJdbcDao.findOneShow(show.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(show);
    }

    // cancelled - soft delete , delete - hard delete

}
